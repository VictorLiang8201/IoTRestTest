<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>歡迎登入IoT Test頁面</title>
<!-- bootstrap -->
<!-- <link -->
<!-- 	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" -->
<!-- 	rel="stylesheet"> -->
<!-- jQuery -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- VueJS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.14/vue.min.js"></script>
</head>

<body>
	<div id="main" align="center">
		<div>
			Tenant Administrator: <br>
			帳號: <input type="text" id="accountT" placeholder="請輸入帳號"><br>
			密碼: <input type="password" id="passwordT" placeholder="請輸入密碼"><br>
			<button style="margin: 5px" @click="showDevicesTenant">顯示您的設備</button>
		</div>

		<div>
			Customer User: <br>
			帳號: <input type="text" id="accountC" placeholder="請輸入帳號"><br>
			密碼: <input type="password" id="passwordC" placeholder="請輸入密碼"><br>
			<button style="margin: 5px" @click="showDevicesCustomer">顯示您的設備</button>
		</div>
		<div id="devices"></div>
		
		<hr style="color: darkgreen;" size="5px">
		<a href="<c:url value='/register' />" class="text-center">回註冊頁面</a>
	</div>
	
</body>

<script>
    new Vue({
        el: '#main',

        methods: {
            showDevicesTenant: () => {
            	let account = $('#accountT').val();
            	let password = $('#passwordT').val();
            	$.ajax({
            		url: '/IoTRestTest/devices?account=' + account + '&password=' + password + '&type=t',
            		type: "GET",
            		success: response => {
            			segment = `
		    						<table border="1">
		    							<tr>
		    								<th>設備擁有客戶之ID</th>
		    								<th>設備名稱</th>
		    								<th>設備類別</th>
		    							</tr>
    					 		  `;
		    			for (let i = 0; i < response.devices.length; i++) {
		    				let device = response.devices[i];
		    				segment += `
		    							<tr>
											<th>` + device.customerId.id + `</th>
											<th>` + device.name + `</th>
											<th>` + device.type + `</th>
										</tr>
		    						   `;
		    			}
						segment += `</table>`;
		        		$('#devices').html(segment);
            		},
            		error: () => {
	            		$('#devices').html('登入失敗，請檢查帳號密碼是否正確');
            		},
            	});
            },
            
            showDevicesCustomer: () => {
            	let account = $('#accountC').val();
            	let password = $('#passwordC').val();
            	$.ajax({
            		url: '/IoTRestTest/devices?account=' + account + '&password=' + password + '&type=c',
            		type: "GET",
            		success: response => {
            			segment = `
		    						<table border="1">
		    							<tr>
		    								<th>設備擁有客戶之ID</th>
		    								<th>設備名稱</th>
		    								<th>設備類別</th>
		    							</tr>
    					 		  `;
		    			for (let i = 0; i < response.devices.length; i++) {
		    				let device = response.devices[i];
		    				segment += `
		    							<tr>
											<th>` + device.customerId.id + `</th>
											<th>` + device.name + `</th>
											<th>` + device.type + `</th>
										</tr>
		    						   `;
		    			}
						segment += `</table>`;
		        		$('#devices').html(segment);
            		},
            		error: () => {
	            		$('#devices').html('登入失敗，請檢查帳號密碼是否正確');
            		},
            	});
            },

        },

    });
</script>

</html>