<%@ page language="java" pageEncoding="utf-8"%>
<html>
<body>
	<form action="https://124.74.239.32/payment/main" method="post" target="_blank">
		<input type="hidden" name="transName" value="KHZF"/>
		<input type="hidden" name="Plain" value="TranAbbr=KHZF|MercDtTm=20150624014803|TermSsn=010101010101|MercCode=983708160009501|TermCode=00000000|TranAmt=88.0|PayBank=abc|AccountType=1|PayType=1|Subject=test|Notice=test|Remark1=test|Remark2=test"/>
		<input type="hidden" name="Signature" value="7899165d113f26955ff73bb5724fccc438f511b6509a1ec209b34eb76a3f3003c0637ab34c4791cf8ccb3bbba0f771f2f83d0fec48cf815401f7f8576d756da344f6864bc1c76fbe2965d28160a81752076970dc5154d5b44e63f4ea4782e163e794b4105e9698758784cf5d8e2ed14e73a68594a9604c6962646fb425fb8323"/>
		<button>提交</button>
	</form>
</body>
</html>
