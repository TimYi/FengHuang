/*  
	-------------------------	jQuery DateTimePicker v0.1.1	----------------------------
	
	Version 0.1.1
	Copyright 2014 Curious Solutions Pvt Ltd and Neha Kadam
	
	https://github.com/CuriousSolutions/DateTimePicker
	
*/


;(function ( $, window, document, undefined ) {
	
		var pluginName = "DateTimePicker";
	
		var defaults = {
		
			mode: "date",
			defaultDate: new Date(),
		
			dateSeparator: "-",
			timeSeparator: ":",
			timeMeridiemSeparator: " ",
			dateTimeSeparator: " ",
		
			dateTimeFormat: "yyyy-MM-dd HH:mm:ss",
			dateFormat: "yyyy-MM-dd",
			timeFormat: "HH:mm",
		
			maxDate: null,
			minDate:  null,
		
			maxTime: null,
			minTime: null,
		
			maxDateTime: null,
			minDateTime: null,
		
			shortDayNames: ["日", "一", "二", "三", "四", "五", "六"],
			fullDayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
			shortMonthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
			fullMonthNames: ["01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"],
		
			titleContentDate: "日期设置",
			titleContentTime: "时间设置",
			titleContentDateTime: "日期时间设置",
		
			buttonsToDisplay: ["HeaderCloseButton", "SetButton", "ClearButton"],
			setButtonContent: "确定",
			clearButtonContent: "取消",
			setValueInTextboxOnEveryClick: false,
		
			animationDuration: 400,
		
			isPopup: true,
			
			parentElement: null,
		
			addEventHandlers: null
		};
	
		var dataObject = {
		
			dCurrentDate: new Date(),
			iCurrentDay: 0,
			iCurrentMonth: 0,
			iCurrentYear: 0,
			iCurrentHour: 0,
			iCurrentMinutes: 0,
			sCurrentMeridiem: "",
			iMaxNumberOfDays: 0,
		
			sDateFormat: "",
			sTimeFormat: "",
			sDateTimeFormat: "",
		
			dMinValue: null,
			dMaxValue: null,
		
			sArrInputDateFormats: [],
			sArrInputTimeFormats: [],
			sArrInputDateTimeFormats: [],
		
			oInputElement: null,
		
			bIs12Hour: false	
		};
	
		function DateTimePicker(element, options)
		{
			this.element = element;
			this.settings = $.extend({}, defaults, options);
			this.dataObject = dataObject;
			this._defaults = defaults;
			this._name = pluginName;
		
			this.init();
		}
	
		$.fn.DateTimePicker = function (options)
		{
			return this.each(function() 
			{
				if (!$.data(this, "plugin_" + pluginName)) 
				{
					$.data(this, "plugin_" + pluginName, new DateTimePicker(this, options));
				}
			});
		};
	
		DateTimePicker.prototype = {
		
			init: function () 
			{
				var dtPickerObj = this;					
			
				dtPickerObj._setDateFormatArray(); // Set DateFormatArray
				dtPickerObj._setTimeFormatArray(); // Set TimeFormatArray
				dtPickerObj._setDateTimeFormatArray(); // Set DateTimeFormatArray
			
				if(dtPickerObj.settings.isPopup)
				{
					dtPickerObj._createPicker();
					$(dtPickerObj.element).addClass("dtpicker-mobile");
				}
				dtPickerObj._addEventHandlersForInput();
			},
		
			_setDateFormatArray: function()
			{
				var dtPickerObj = this;
			
				dtPickerObj.dataObject.sArrInputDateFormats = new Array();		
				var sDate = "";
			
				//  "dd-MM-yyyy"
				sDate = "dd" + dtPickerObj.settings.dateSeparator + "MM" + dtPickerObj.settings.dateSeparator + "yyyy";
				dtPickerObj.dataObject.sArrInputDateFormats.push(sDate);
			
				//  "MM-dd-yyyy"
				sDate = "MM" + dtPickerObj.settings.dateSeparator + "dd" + dtPickerObj.settings.dateSeparator + "yyyy";
				dtPickerObj.dataObject.sArrInputDateFormats.push(sDate);
			
				//  "yyyy-MM-dd"
				sDate = "yyyy" + dtPickerObj.settings.dateSeparator + "MM" + dtPickerObj.settings.dateSeparator + "dd";
				dtPickerObj.dataObject.sArrInputDateFormats.push(sDate);
			
				// "dd-MMM-yyyy"
				sDate = "dd" + dtPickerObj.settings.dateSeparator + "MMM" + dtPickerObj.settings.dateSeparator + "yyyy";
				dtPickerObj.dataObject.sArrInputDateFormats.push(sDate);
			},
		
			_setTimeFormatArray: function()
			{
				var dtPickerObj = this;
			
				dtPickerObj.dataObject.sArrInputTimeFormats = new Array();
				var sTime = "";
			
				//  "hh:mm AA"
				sTime = "hh" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeMeridiemSeparator + "AA";
				dtPickerObj.dataObject.sArrInputTimeFormats.push(sTime);
			
				//  "HH:mm"
				sTime = "HH" + dtPickerObj.settings.timeSeparator + "mm";
				dtPickerObj.dataObject.sArrInputTimeFormats.push(sTime);
			},
		
			_setDateTimeFormatArray: function()
			{
				var dtPickerObj = this;
			
				dtPickerObj.dataObject.sArrInputDateTimeFormats = new Array();
				var sDate = "", sTime = "", sDateTime = "";
			
				//  "yyyy-MM-dd HH:mm:ss"
				sDate = "yyyy" + dtPickerObj.settings.dateSeparator + "MM" + dtPickerObj.settings.dateSeparator + "dd";
				sTime = "HH" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
			
				//  "yyyy-MM-dd hh:mm:ss AA"
				sDate = "yyyy" + dtPickerObj.settings.dateSeparator + "MM" + dtPickerObj.settings.dateSeparator + "dd";
				sTime = "hh" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss" + dtPickerObj.settings.timeMeridiemSeparator + "AA";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
				
				//  "dd-MM-yyyy HH:mm:ss"
				sDate = "dd" + dtPickerObj.settings.dateSeparator + "MM" + dtPickerObj.settings.dateSeparator + "yyyy";
				sTime = "HH" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
			
				//  "dd-MM-yyyy hh:mm:ss AA"
				sDate = "dd" + dtPickerObj.settings.dateSeparator + "MM" + dtPickerObj.settings.dateSeparator + "yyyy";
				sTime = "hh" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss" + dtPickerObj.settings.timeMeridiemSeparator + "AA";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
			
				//  "MM-dd-yyyy HH:mm:ss"
				sDate = "MM" + dtPickerObj.settings.dateSeparator + "dd" + dtPickerObj.settings.dateSeparator + "yyyy";
				sTime = "HH" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
			
				//  "MM-dd-yyyy hh:mm:ss AA"
				sDate = "MM" + dtPickerObj.settings.dateSeparator + "dd" + dtPickerObj.settings.dateSeparator + "yyyy";
				sTime = "hh" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss" + dtPickerObj.settings.timeMeridiemSeparator + "AA";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
			
				//  "dd-MMM-yyyy hh:mm:ss"
				sDate = "dd" + dtPickerObj.settings.dateSeparator + "MMM" + dtPickerObj.settings.dateSeparator + "yyyy";
				sTime = "hh" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
				
				//  "dd-MMM-yyyy hh:mm:ss AA"
				sDate = "dd" + dtPickerObj.settings.dateSeparator + "MMM" + dtPickerObj.settings.dateSeparator + "yyyy";
				sTime = "hh" + dtPickerObj.settings.timeSeparator + "mm" + dtPickerObj.settings.timeSeparator + "ss" + dtPickerObj.settings.timeMeridiemSeparator + "AA";
				sDateTime = sDate + dtPickerObj.settings.dateTimeSeparator + sTime;
				dtPickerObj.dataObject.sArrInputDateTimeFormats.push(sDateTime);
			},
		
			_createPicker: function()
			{
				var dtPickerObj = this;
			
				$(dtPickerObj.element).addClass("dtpicker-overlay");
				$(".dtpicker-overlay").click(function(e)
				{
					dtPickerObj._hidePicker();
				});
			
				var sTempStr = "";	
				sTempStr += "<div class='dtpicker-bg'>";
				sTempStr += "<div class='dtpicker-cont'>";
				sTempStr += "<div class='dtpicker-content'>";
				sTempStr += "<div class='dtpicker-subcontent'>";
				sTempStr += "</div>";
				sTempStr += "</div>";
				sTempStr += "</div>";
				sTempStr += "</div>";
				$(dtPickerObj.element).html(sTempStr);
			},
		
			_addEventHandlersForInput: function()
			{
				var dtPickerObj = this;
			
				dtPickerObj.dataObject.oInputElement = null;
								
				var oArrElements = undefined;
 
 				if (dtPickerObj.settings.parentElement != undefined)
 				{
 					$(dtPickerObj.settings.parentElement).find("input[type='date'], input[type='time'], input[type='datetime']").each(function()
					{
 						var sType = $(this).attr("type");
 						$(this).attr("type", "text");
 						$(this).attr("data-field", sType);
 					});
 
					oArrElements = $(dtPickerObj.settings.parentElement).find("[data-field='date'], [data-field='time'], [data-field='datetime']");
				}
 				else
				{
					$("input[type='date'], input[type='time'], input[type='datetime']").each(function()
 					{
 						var sType = $(this).attr("type");
 						$(this).attr("type", "text");
 						$(this).attr("data-field", sType);
 					});
					
					oArrElements = $("[data-field='date'], [data-field='time'], [data-field='datetime']");
				}
				
				$(oArrElements).unbind("focus", dtPickerObj._inputFieldFocus);
				$(oArrElements).on("focus", {"obj": dtPickerObj}, dtPickerObj._inputFieldFocus);
			
				$(oArrElements).not('input').click(function(e)
				{
					if(dtPickerObj.dataObject.oInputElement == null)
					{
						dtPickerObj.showDateTimePicker(this);
					}
				});
				
				$(oArrElements).click(function(e)
				{
					e.stopPropagation();
				});
						
				if(dtPickerObj.settings.addEventHandlers)
					dtPickerObj.settings.addEventHandlers.call(dtPickerObj);
			},
		
			_inputFieldFocus: function(e)
			{
				var dtPickerObj = e.data.obj;
			
				if(dtPickerObj.dataObject.oInputElement == null)
				{
					dtPickerObj.showDateTimePicker(e.target);
				}
			},
		
			showDateTimePicker: function(element)
			{
				var dtPickerObj = this;
			
				if(dtPickerObj.dataObject.oInputElement == null)
				{
					dtPickerObj.dataObject.oInputElement = element;
				
					var sMode = $(element).data("field") || "";
					var sMinValue = $(element).data("min") || "";
					var sMaxValue = $(element).data("max") || "";
					var sFormat = $(element).data("format") || "";
					var sView = $(element).data("view") || "";
					var sStartEnd = $(element).data("startend") || "";
					var sStartEndElem = $(element).data("startendelem") || "";
					var sCurrent = dtPickerObj._getValueOfElement(element) || "";
				
					if(sView != "")
					{
						if(dtPickerObj._compare(sView, "Popup"))
							dtPickerObj.setIsPopup(true);
						else 
							dtPickerObj.setIsPopup(false);
					}
				
					if(! dtPickerObj.settings.isPopup)
					{
						dtPickerObj._createPicker();
					
						var iElemTop = $(dtPickerObj.dataObject.oInputElement).offset().top + $(dtPickerObj.dataObject.oInputElement).outerHeight();
						var iElemLeft = $(dtPickerObj.dataObject.oInputElement).offset().left;
						var iElemWidth =  $(dtPickerObj.dataObject.oInputElement).outerWidth();
					
						$(dtPickerObj.element).css({position: "absolute", top: iElemTop, left: iElemLeft, width: iElemWidth, height: "auto"});
					}
				
					dtPickerObj._showPicker(sMode, sMinValue, sMaxValue, sFormat, sCurrent, element, sStartEnd, sStartEndElem);
				}
			},
		
			_setButtonAction: function()
			{
				var dtPickerObj = this;
			
				if(dtPickerObj.dataObject.oInputElement != null)
				{
					var sOutput = dtPickerObj._setOutput();
					dtPickerObj._setValueOfElement(sOutput);
					dtPickerObj._hidePicker();
				}
			},
		
			_setOutput: function()
			{
				var dtPickerObj = this;
			
				var sOutput = "";
			
				var iDate = dtPickerObj.dataObject.dCurrentDate.getDate();
				var iMonth = dtPickerObj.dataObject.dCurrentDate.getMonth();
				var iYear = dtPickerObj.dataObject.dCurrentDate.getFullYear();
				var iHour = dtPickerObj.dataObject.dCurrentDate.getHours();
				var iMinutes = dtPickerObj.dataObject.dCurrentDate.getMinutes();
			
				if(dtPickerObj._compare(dtPickerObj.settings.mode, "date"))
				{
					if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[0]))
					{
						iMonth++;
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = (iMonth < 10) ? ("0" + iMonth) : iMonth;
						
						sOutput = sDate + dtPickerObj.settings.dateSeparator + sMonth + dtPickerObj.settings.dateSeparator + iYear;
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[1]))
					{
						iMonth++;
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = (iMonth < 10) ? ("0" + iMonth) : iMonth;
						
						sOutput = sMonth + dtPickerObj.settings.dateSeparator + sDate + dtPickerObj.settings.dateSeparator + iYear;
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[2]))
					{
						iMonth++;
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = (iMonth < 10) ? ("0" + iMonth) : iMonth;
						
						sOutput = iYear + dtPickerObj.settings.dateSeparator + sMonth + dtPickerObj.settings.dateSeparator + sDate;
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[3]))
					{
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = dtPickerObj.settings.shortMonthNames[iMonth];
					
						sOutput = sDate + dtPickerObj.settings.dateSeparator + sMonth + dtPickerObj.settings.dateSeparator + iYear;
					}
				}
				else if(dtPickerObj._compare(dtPickerObj.settings.mode, "time"))
				{
					if(dtPickerObj._compare(dtPickerObj.dataObject.sTimeFormat, dtPickerObj.dataObject.sArrInputTimeFormats[0]))
					{
						var sMeridiem = "";
						if(iHour > 12)
						{
							iHour -= 12;
							sMeridiem = "PM";
						}
						else if(iHour == 12 && iMinutes > 0)
						{
							sMeridiem = "PM";
						}
						else
						{
							sMeridiem = "AM";
						}
					
						var sHour = (iHour < 10) ? ("0" + iHour) : iHour;
						var sMinutes = (iMinutes < 10) ? ("0" + iMinutes) : iMinutes;
					
						sOutput = sHour + dtPickerObj.settings.timeSeparator + sMinutes + dtPickerObj.settings.timeMeridiemSeparator + sMeridiem;
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sTimeFormat, dtPickerObj.dataObject.sArrInputTimeFormats[1]))
					{
						var sHour = (iHour < 10) ? ("0" + iHour) : iHour;
						var sMinutes = (iMinutes < 10) ? ("0" + iMinutes) : iMinutes;
					
						sOutput = sHour + dtPickerObj.settings.timeSeparator + sMinutes;
					}
				}
				else if(dtPickerObj._compare(dtPickerObj.settings.mode, "datetime"))
				{
					var sDateStr = "";
					var sTimeStr = "";
				
					if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[0]) || dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[1]))
					{
						iMonth++;
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = (iMonth < 10) ? ("0" + iMonth) : iMonth;
					
						sDateStr = sDate + dtPickerObj.settings.dateSeparator + sMonth + dtPickerObj.settings.dateSeparator + iYear;
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[2]) || dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[3]))
					{
						iMonth++;
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = (iMonth < 10) ? ("0" + iMonth) : iMonth;
					
						sDateStr = sMonth + dtPickerObj.settings.dateSeparator + sDate + dtPickerObj.settings.dateSeparator + iYear;
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[4]) || dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[5]))
					{
						iMonth++;
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = (iMonth < 10) ? ("0" + iMonth) : iMonth;
					
						sDateStr = iYear + dtPickerObj.settings.dateSeparator + sMonth + dtPickerObj.settings.dateSeparator + sDate;
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[6]) || dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[7]))
					{
						var sDate = (iDate < 10) ? ("0" + iDate) : iDate;
						var sMonth = dtPickerObj.settings.shortMonthNames[iMonth];
					
						sDateStr = sDate + dtPickerObj.settings.dateSeparator + sMonth + dtPickerObj.settings.dateSeparator + iYear;
					}
				
					if(dtPickerObj.dataObject.bIs12Hour)
					{
						var sMeridiem = "";
						if(iHour > 12)
						{
							iHour -= 12;
							sMeridiem = "PM";
						}
						else if(iHour == 12 && iMinutes > 0)
						{
							sMeridiem = "PM";
						}
						else
						{
							sMeridiem = "AM";
						}
					
						var sHour = (iHour < 10) ? ("0" + iHour) : iHour;
						var sMinutes = (iMinutes < 10) ? ("0" + iMinutes) : iMinutes;
					
						sTimeStr = sHour + dtPickerObj.settings.timeSeparator + sMinutes + dtPickerObj.settings.timeMeridiemSeparator + sMeridiem;
					}
					else
					{
						var sHour = (iHour < 10) ? ("0" + iHour) : iHour;
						var sMinutes = (iMinutes < 10) ? ("0" + iMinutes) : iMinutes;
					
						sTimeStr = sHour + dtPickerObj.settings.timeSeparator + sMinutes;
					}
				
					sOutput = sDateStr + dtPickerObj.settings.dateTimeSeparator + sTimeStr;
				}
			
				return sOutput;
			},
		
			_clearButtonAction: function()
			{
				var dtPickerObj = this;
			
				if(dtPickerObj.dataObject.oInputElement != null)
				{
					dtPickerObj._setValueOfElement("");
				}
				dtPickerObj._hidePicker();
			},
		
			_setOutputOnIncrementOrDecrement: function()
			{
				var dtPickerObj = this;
			
				if(dtPickerObj.dataObject.oInputElement != null && dtPickerObj.settings.setValueInTextboxOnEveryClick)
				{
					var sOutput = dtPickerObj._setOutput();
					dtPickerObj._setValueOfElement(sOutput);
				}
			},
		
			_showPicker: function(sMode, sMinValue, sMaxValue, sFormat, sCurrent, oElement, sStartEnd, sStartEndElem)
			{
				var dtPickerObj = this;
			
				if(sMode != "")
					dtPickerObj.settings.mode = sMode;
			
				dtPickerObj.dataObject.dMinValue = null;
				dtPickerObj.dataObject.dMaxValue = null;
				dtPickerObj.dataObject.bIs12Hour = false;
			
				if(dtPickerObj._compare(dtPickerObj.settings.mode, "date"))
				{
					var sMin = sMinValue || dtPickerObj.settings.minDate;
					var sMax = sMaxValue || dtPickerObj.settings.maxDate;
				
					var sDateFormat = sFormat || dtPickerObj.settings.dateFormat;
					if(sDateFormat != "" && sDateFormat != null)
						dtPickerObj.dataObject.sDateFormat = sDateFormat;
				
					if(sMin != "" && sMin != null)
						dtPickerObj.dataObject.dMinValue = dtPickerObj._parseDate(sMin);
					if(sMax != "" && sMax != null)
						dtPickerObj.dataObject.dMaxValue = dtPickerObj._parseDate(sMax);
				
					if(sStartEnd != "" && (dtPickerObj._compare(sStartEnd, "start") || dtPickerObj._compare(sStartEnd, "end")) && sStartEndElem != "")
					{
						if($(sStartEndElem).length >= 1)
						{
							var sTempDate = dtPickerObj._getValueOfElement($(sStartEndElem));
							if(sTempDate != "")
							{
								var dTempDate = dtPickerObj._parseDate(sTempDate);
								if(dtPickerObj._compare(sStartEnd, "start"))
								{
									if(sMax != "" && sMax != null)
									{
										if(dtPickerObj._compareDates(dTempDate, dtPickerObj.dataObject.dMaxValue) == 2)
											dtPickerObj.dataObject.dMaxValue = new Date(dTempDate);
									}
									else
										dtPickerObj.dataObject.dMaxValue = new Date(dTempDate);
								}
								else if(dtPickerObj._compare(sStartEnd, "end"))
								{
									if(sMin != "" && sMin != null)
									{
										if(dtPickerObj._compareDates(dTempDate, dtPickerObj.dataObject.dMinValue) == 3)
											dtPickerObj.dataObject.dMinValue = new Date(dTempDate);
									}
									else
										dtPickerObj.dataObject.dMinValue = new Date(dTempDate);
								}
							}
						}
					}
				
					dtPickerObj.dataObject.dCurrentDate = dtPickerObj._parseDate(sCurrent);
					dtPickerObj.dataObject.dCurrentDate.setHours(0);
					dtPickerObj.dataObject.dCurrentDate.setMinutes(0);
					dtPickerObj.dataObject.dCurrentDate.setSeconds(0);
				}
				else if(dtPickerObj._compare(dtPickerObj.settings.mode, "time"))
				{
					var sMin = sMinValue || dtPickerObj.settings.minTime;
					var sMax = sMaxValue || dtPickerObj.settings.maxTime;
				
					var sTimeFormat = sFormat || dtPickerObj.settings.timeFormat;
					if(sTimeFormat != "" && sTimeFormat != null)
						dtPickerObj.dataObject.sTimeFormat = sTimeFormat;
				
					if(sMin != "" && sMin != null)
						dtPickerObj.dataObject.dMinValue = dtPickerObj._parseTime(sMin);
					if(sMax != "" && sMax != null)
						dtPickerObj.dataObject.dMaxValue = dtPickerObj._parseTime(sMax);
				
					if(sStartEnd != "" && (dtPickerObj._compare(sStartEnd, "start") || dtPickerObj._compare(sStartEnd, "end")) && sStartEndElem != "")
					{
						if($(sStartEndElem).length >= 1)
						{
							var sTempTime = dtPickerObj._getValueOfElement($(sStartEndElem));
						
							if(sTempTime != "")
							{
								var dTempTime = dtPickerObj._parseTime(sTempTime);
								if(dtPickerObj._compare(sStartEnd, "start"))
								{
									dTempTime.setMinutes(dTempTime.getMinutes() - 1);
									if(sMax != "" && sMax != null)
									{
										if(dtPickerObj._compareTime(dTempTime, dtPickerObj.dataObject.dMaxValue) == 2)
											dtPickerObj.dataObject.dMaxValue = new Date(dTempTime);
									}
									else
										dtPickerObj.dataObject.dMaxValue = new Date(dTempTime);
								}
								else if(dtPickerObj._compare(sStartEnd, "end"))
								{
									dTempTime.setMinutes(dTempTime.getMinutes() + 1);
									if(sMin != "" && sMin != null)
									{
										if(dtPickerObj._compareTime(dTempTime, dtPickerObj.dataObject.dMinValue) == 3)
											dtPickerObj.dataObject.dMinValue = new Date(dTempTime);
									}
									else
										dtPickerObj.dataObject.dMinValue = new Date(dTempTime);
								}
							}
						}
					}
				
					dtPickerObj.dataObject.dCurrentDate = dtPickerObj._parseTime(sCurrent);
					dtPickerObj.dataObject.bIs12Hour = dtPickerObj._compare(dtPickerObj.dataObject.sTimeFormat, dtPickerObj.dataObject.sArrInputTimeFormats[0]);
				}
				else if(dtPickerObj._compare(dtPickerObj.settings.mode, "datetime"))
				{
					var sMin = sMinValue || dtPickerObj.settings.minDateTime;
					var sMax = sMaxValue || dtPickerObj.settings.maxDateTime;
				
					var sDateTimeFormat = sFormat || dtPickerObj.settings.dateTimeFormat;
					if(sDateTimeFormat != "" && sDateTimeFormat != null)
						dtPickerObj.dataObject.sDateTimeFormat = sDateTimeFormat;
				
					if(sMin != "" && sMin != null)
						dtPickerObj.dataObject.dMinValue = dtPickerObj._parseDateTime(sMin);
					if(sMax != "" && sMax != null)
						dtPickerObj.dataObject.dMaxValue = dtPickerObj._parseDateTime(sMax);
				
					if(sStartEnd != "" && (dtPickerObj._compare(sStartEnd, "start") || dtPickerObj._compare(sStartEnd, "end")) && sStartEndElem != "")
					{
						if($(sStartEndElem).length >= 1)
						{
							var sTempDateTime = dtPickerObj._getValueOfElement($(sStartEndElem));
							if(sTempDateTime != "")
							{
								var dTempDateTime = dtPickerObj._parseDateTime(sTempDateTime);
								if(dtPickerObj._compare(sStartEnd, "start"))
								{
									if(sMax != "" && sMax != null)
									{
										if(dtPickerObj._compareDateTime(dTempDateTime, dtPickerObj.dataObject.dMaxValue) == 2)
											dtPickerObj.dataObject.dMaxValue = new Date(dTempDateTime);
									}
									else
										dtPickerObj.dataObject.dMaxValue = new Date(dTempDateTime);
								}
								else if(dtPickerObj._compare(sStartEnd, "end"))
								{
									if(sMin != "" && sMin != null)
									{
										if(dtPickerObj._compareDateTime(dTempDateTime, dtPickerObj.dataObject.dMinValue) == 3)
											dtPickerObj.dataObject.dMinValue = new Date(dTempDateTime);
									}
									else
										dtPickerObj.dataObject.dMinValue = new Date(dTempDateTime);
								}
							}
						}
					}
				
					dtPickerObj.dataObject.dCurrentDate = dtPickerObj._parseDateTime(sCurrent);
				
					dtPickerObj.dataObject.bIs12Hour = dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[1]) ||
					dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[3]) ||
					dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[5]) ||
					dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[7]);
				}
			
				dtPickerObj._setVariablesForDate();
				dtPickerObj._modifyPicker();
				$(dtPickerObj.element).fadeIn(dtPickerObj.settings.animationDuration);
			},
		
			_hidePicker: function(iDuration)
			{
				var dtPickerObj = this;
			
				if(dtPickerObj.dataObject.oInputElement != null)
				{
					$(dtPickerObj.dataObject.oInputElement).blur();
					dtPickerObj.dataObject.oInputElement = null;
				}
			
				$(dtPickerObj.element).fadeOut(iDuration || dtPickerObj.settings.animationDuration);
				setTimeout(function()
				{
					$(dtPickerObj.element).find('.dtpicker-subcontent').html("");
				}, (iDuration || dtPickerObj.settings.animationDuration));
			},
		
			_modifyPicker: function()
			{
				var dtPickerObj = this;
			
				var sTitleContent, iNumberOfColumns;
				var sArrFields = new Array();
				if(dtPickerObj._compare(dtPickerObj.settings.mode, "date"))
				{
					sTitleContent = dtPickerObj.settings.titleContentDate;
					iNumberOfColumns = 3;
				
					if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[0]))  // "dd-MM-yyyy"
					{
						sArrFields = ["day", "month", "year"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[1]))  // "MM-dd-yyyy"
					{
						sArrFields = ["month", "day", "year"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[2]))  // "yyyy-MM-dd"
					{
						sArrFields = ["year", "month", "day"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateFormat, dtPickerObj.dataObject.sArrInputDateFormats[3]))  // "dd-MMM-yyyy"
					{
						sArrFields = ["day", "month", "year"];
					}
				}
				else if(dtPickerObj._compare(dtPickerObj.settings.mode, "time"))
				{
					sTitleContent = dtPickerObj.settings.titleContentTime;
					if(dtPickerObj._compare(dtPickerObj.dataObject.sTimeFormat, dtPickerObj.dataObject.sArrInputTimeFormats[0]))
					{
						iNumberOfColumns = 3;
						sArrFields = ["hour", "minutes", "meridiem"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sTimeFormat, dtPickerObj.dataObject.sArrInputTimeFormats[1]))
					{
						iNumberOfColumns = 2;
						sArrFields = ["hour", "minutes"];
					}
				}
				else if(dtPickerObj._compare(dtPickerObj.settings.mode, "datetime"))
				{
					sTitleContent = dtPickerObj.settings.titleContentDateTime;
				
					if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[0]))
					{
						iNumberOfColumns = 5;
						sArrFields = ["day", "month", "year", "hour", "minutes"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[1]))
					{
						iNumberOfColumns = 6;
						sArrFields = ["day", "month", "year", "hour", "minutes", "meridiem"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[2]))
					{
						iNumberOfColumns = 5;
						sArrFields = ["month", "day", "year", "hour", "minutes"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[3]))
					{
						iNumberOfColumns = 6;
						sArrFields = ["month", "day", "year", "hour", "minutes", "meridiem"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[4]))
					{
						iNumberOfColumns = 5;
						sArrFields = ["year", "month", "day", "hour", "minutes"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[5]))
					{
						iNumberOfColumns = 6;
						sArrFields = ["year", "month", "day", "hour", "minutes", "meridiem"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[6]))
					{
						iNumberOfColumns = 5;
						sArrFields = ["day", "month", "year", "hour", "minutes"];
					}
					else if(dtPickerObj._compare(dtPickerObj.dataObject.sDateTimeFormat, dtPickerObj.dataObject.sArrInputDateTimeFormats[7]))
					{
						iNumberOfColumns = 6;
						sArrFields = ["day", "month", "year", "hour", "minutes", "meridiem"];
					}
				}
				var sColumnClass = "dtpicker-comp" + iNumberOfColumns;
			
				//--------------------------------------------------------------------
				
				var bDisplayHeaderCloseButton = false;
				var bDisplaySetButton = false;
				var bDisplayClearButton = false;
				
				for(var iTempIndex = 0; iTempIndex < dtPickerObj.settings.buttonsToDisplay.length; iTempIndex++)
				{
					if(dtPickerObj._compare(dtPickerObj.settings.buttonsToDisplay[iTempIndex], "HeaderCloseButton"))
						bDisplayHeaderCloseButton = true;
					else if(dtPickerObj._compare(dtPickerObj.settings.buttonsToDisplay[iTempIndex], "SetButton"))
						bDisplaySetButton = true;
					else if(dtPickerObj._compare(dtPickerObj.settings.buttonsToDisplay[iTempIndex], "ClearButton"))
						bDisplayClearButton = true;
				}
			
				var sHeader = "";
				sHeader += "<div class='dtpicker-header'>";
				sHeader += "<div class='dtpicker-title'>" + sTitleContent + "</div>";
				if(bDisplayHeaderCloseButton)
					sHeader += "<a class='dtpicker-close'>X</a>";
				sHeader += "<div class='dtpicker-value'></div>";
				sHeader += "</div>";
			
				//--------------------------------------------------------------------
			
				var sDTPickerComp = "";
				sDTPickerComp += "<div class='dtpicker-components'>";
			
				for(var iTempIndex = 0; iTempIndex < iNumberOfColumns; iTempIndex++)
				{
					var sFieldName = sArrFields[iTempIndex];
				
					sDTPickerComp += "<div class='dtpicker-compOutline " + sColumnClass + "'>";
					sDTPickerComp += "<div class='dtpicker-comp " + sFieldName + "'>";
					sDTPickerComp += "<a class='dtpicker-compButton increment'>+</a>";
					sDTPickerComp += "<input type='text' class='dtpicker-compValue'></input>";
					sDTPickerComp += "<a class='dtpicker-compButton decrement'>-</a>";
					sDTPickerComp += "</div>";
					sDTPickerComp += "</div>";
				}
			
				sDTPickerComp += "</div>";
			
				//--------------------------------------------------------------------
			
				var sButtonContClass = "";
				if(bDisplaySetButton && bDisplayClearButton)
					sButtonContClass = " dtpicker-twoButtons";
				else
					sButtonContClass = " dtpicker-singleButton";
			
				var sDTPickerButtons = "";
				sDTPickerButtons += "<div class='dtpicker-buttonCont" + sButtonContClass + "'>";
				if(bDisplaySetButton)
					sDTPickerButtons += "<a class='dtpicker-button dtpicker-buttonSet'>" + dtPickerObj.settings.setButtonContent + "</a>";
				if(bDisplayClearButton)
					sDTPickerButtons += "<a class='dtpicker-button dtpicker-buttonClear'>" + dtPickerObj.settings.clearButtonContent + "</a>";
				sDTPickerButtons += "</div>";
			
				//--------------------------------------------------------------------
			
				sTempStr = sHeader + sDTPickerComp + sDTPickerButtons;
			
				$(dtPickerObj.element).find('.dtpicker-subcontent').html(sTempStr);
			
				dtPickerObj._setCurrentDate();
				dtPickerObj._addEventHandlersForPicker();
			},
		
			_addEventHandlersForPicker: function()
			{
				var dtPickerObj = this;
			
				$(document).click(function(e)
				{
					dtPickerObj._hidePicker();
				});
			
				$(".dtpicker-cont *").click(function(e)
				{
					e.stopPropagation();
				});
				
				$('.dtpicker-compValue').not('.month .dtpicker-compValue, .meridiem .dtpicker-compValue').keyup(function() 
				{ 
					this.value = this.value.replace(/[^0-9\.]/g,'');
				});
			
				$('.dtpicker-compValue').blur(function()
				{
					dtPickerObj._getValuesFromInputBoxes();
					dtPickerObj._setCurrentDate();
				
					if($(this).parent().parent().is(':last-child'))
					{
						dtPickerObj._setButtonAction();
					}
				});
			
				$(".dtpicker-compValue").keyup(function()
				{
					var $oTextField = $(this);
				
					var sTextBoxVal = $oTextField.val();
					var iLength = sTextBoxVal.length;
				
					if($oTextField.parent().hasClass("day") || $oTextField.parent().hasClass("hour") || $oTextField.parent().hasClass("minutes") || $oTextField.parent().hasClass("meridiem"))
					{
						if(iLength > 2)
						{
							var sNewTextBoxVal = sTextBoxVal.slice(0, 2);
							$oTextField.val(sNewTextBoxVal);
						}
					}
					else if($oTextField.parent().hasClass("month"))
					{
						if(iLength > 3)
						{
							var sNewTextBoxVal = sTextBoxVal.slice(0, 3);
							$oTextField.val(sNewTextBoxVal);
						}
					}
					else if($oTextField.parent().hasClass("year"))
					{
						if(iLength > 4)
						{
							var sNewTextBoxVal = sTextBoxVal.slice(0, 4);
							$oTextField.val(sNewTextBoxVal);
						}
					}					
				});
				
				$(document).keyup(function()
				{
					if(! $('.dtpicker-compValue').is(':focus'))
					{
						dtPickerObj._hidePicker();
					}
				});
			
				//-----------------------------------------------------------------------
			
				$(dtPickerObj.element).find('.dtpicker-close').click(function(e)
				{
					dtPickerObj._hidePicker();
				});
			
				$(dtPickerObj.element).find('.dtpicker-buttonSet').click(function(e)
				{
					dtPickerObj._setButtonAction();
				});
			
				$(dtPickerObj.element).find('.dtpicker-buttonClear').click(function(e)
				{
					dtPickerObj._clearButtonAction();
				});
			
				// ----------------------------------------------------------------------------
			
				$(dtPickerObj.element).find(".day .increment").click(function(e)
				{
					dtPickerObj.dataObject.iCurrentDay++;
					dtPickerObj._setCurrentDate();
					dtPickerObj._setOutputOnIncrementOrDecrement();
				});
			
				$(dtPickerObj.element).find(".day .decrement").click(function(e)
				{
					dtPickerObj.dataObject.iCurrentDay--;
					dtPickerObj._setCurrentDate();
					dtPickerObj._setOutputOnIncrementOrDecrement();
				});
			
				$(dtPickerObj.element).find(".month .increment").click(function(e)
				{
					dtPickerObj.dataObject.iCurrentMonth++;
					dtPickerObj._setCurrentDate();
					dtPickerObj._setOutputOnIncrementOrDecrement();
				});
			
				$(dtPickerObj.element).find(".month .decrement").click(function(e)
				{
					dtPickerObj.dataObject.iCurrentMonth--;
					dtPickerObj._setCurrentDate();
					dtPickerObj._setOutputOnIncrementOrDecrement();
				});
			
				$(dtPickerObj.element).find(".year .increment").click(function(e)
				{
					dtPickerObj.dataObject.iCurrentYear++;
					dtPickerObj._setCurrentDate();
					dtPickerObj._setOutputOnIncrementOrDecrement();
				});
			
				$(dtPickerObj.element).find(".year .decrement").click(function(e)
				{
					dtPickerObj.dataObject.iCurrentYear--;
					dtPickerObj._setCurrentDate();
					dtPickerObj._setOutputOnIncrementOrDecrement()