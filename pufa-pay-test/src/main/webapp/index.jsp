<%@ page language="java" pageEncoding="utf-8"%>
<html>
<body>
	<form action="https://124.74.239.32/payment/main" method="post" target="_blank">
		<input type="hidden" name="transName" value="KHZF"/>
		<input type="hidden" name="Plain" value="TranAbbr=KHZF|MercDtTm=20150619110338|TermSsn=010101010101|MercCode=983708160009501|TermCode=00000000|TranAmt=88.0|PayBank=abc|AccountType=1|PayType=1|Subject=test|Notice=test|Remark1=test|Remark2=test"/>
		<input type="hidden" name="Signature" value="24b92057a9f32b707ff38bd1ce80c596a70f21e24c5f1cf90163f8a7c51f636800c190b75a5972e6392b9d928603b3f3b2e1102980afe333d7e59b25a31ca54a7858db1f9105064531821fc2949f0376ab8b772e84d3a8f93423d3621df7e6c9e8ec34dbf3ec32262b0ac7ece22aece0b021df6e4334586d9ac90f6710165e1a"/>
		<button>提交</button>
	</form>
</body>
</html>
