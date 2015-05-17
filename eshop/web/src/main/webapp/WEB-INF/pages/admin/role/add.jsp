<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<my:admin tab="authentication">
	<jsp:attribute name="nav">
		<my:auth-nav tab="role"></my:auth-nav>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script id="user-template" type="text/html">
			<div id="{{id}}" style="display:inline">
			    <input type="hidden" value="{{id}}" name="userids">
			    <button onclick="deleteUser('{{id}}')" type="button" class="btn btn-default">{{username}}</button>
			</div>
		</script>
		<script id="authority-template" type="text/html">
			<div id="{{id}}" style="display:inline">
			    <input type="hidden" value="{{id}}" name="authorityids">
			    <button onclick="deleteAuthority('{{id}}')" type="button" class="btn btn-default">{{authority}}</button>
			</div>
		</script>
		<script>
			function addUser() {
				var username=$("#user").val();
				post('<c:url value="/admin/user/username/"/>'+username,function(data){
					if($('#'+data.id).size()>0) {
						alert("请勿重复添加用户！");
						return;
					}
					var html=template('user-template',data);
					$(html).appendTo("#users");
				})
			}
			function deleteUser(id) {
				$("#"+id).remove();
			}
			function auth(id) {
				var authority=$("#"+id);
				if(authority.hasClass("selected")) {
					authority.find("button").removeClass("btn-success");
					authority.find("input").attr("name",null);
					authority.removeClass("selected");
				} else {
					authority.find("button").addClass("btn-success");
					authority.find("input").attr("name","authorityids");
					authority.addClass("selected");
				}
			}
		</script>
	</jsp:attribute>
	<jsp:body>
		<form action="./" role="form" class="form-horizontal" method="post">
           <div class="form-group">
               <label for="name" class="col-sm-2 control-label">名称:</label>
               <div class="col-sm-10">
                   <input type="text" class="form-control" name="name" placeholder="please input name">
               </div>
           </div>
           <div class="form-group">
               <label for="description" class="col-sm-2 control-label">描述:</label>
               <div class="col-sm-10">
                   <input type="text" class="form-control" name="description" placeholder="please input description">
               </div>
           </div>
           <div class="form-group">
               <label for="description" class="col-sm-2 control-label">用户:</label>
               <div class="col-sm-10">
                  	<div class="form-group">		               
		               <div class="col-sm-10">
		                   <input id="user" type="text" class="form-control" placeholder="请输入用户名">
		               </div>
		               <div class="col-sm-2"><button onclick="addUser()" type="button" class="btn btn-default">添加</button></div>
		            </div>
		            <div id="users">
		            </div>	            
               </div>
           </div>
           <div class="form-group">
               <label for="description" class="col-sm-2 control-label">权限:</label>
               <div class="col-sm-10">
		            <div id="authorities">
		            	<c:forEach items="${authorities }" var="authority">
		            		<div id="${authority.id }" style="display:inline">
		            			<input type="hidden" value="${authority.id }">
							    <button onclick="auth('${authority.id }')" type="button" class="btn btn-default">${authority.authority }</button>
							</div>
		            	</c:forEach>
		            </div>               
               </div>
           </div>
           <div class="form-group">
			   <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">保存</button>
			   </div>
		   </div>
       </form>
	</jsp:body>
</my:admin>