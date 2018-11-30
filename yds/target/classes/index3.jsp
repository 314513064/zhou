<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>page1</title>
    <style>
    	#myCanvas{
    		background:url('/yds/src/main/webapp/image/cat.jpg') no-repeat center;
    		background-size:cover;
    	}
    </style>
</head>
<body>
    Welcome<br/><input id="text" type="text"/>
    <button onclick="send()">鍙戦�佹秷鎭�</button>
    <hr/>
    <button onclick="closeWebSocket()">鍏抽棴WebSocket杩炴帴</button>
    <hr/>
    <img src='/yds/image/cat.jpg'>
    <canvas id='myCanvas' width:'500';height:'500' style:'boder:1px solid #DDD; display:block;margin:0 auto;'></canvas>
</body>

<script type="text/javascript">
    var websocket = null;
    //鍒ゆ柇褰撳墠娴忚鍣ㄦ槸鍚︽敮鎸乄ebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/yds/websocket/1000/2000");
    }
    else {
        alert('褰撳墠娴忚鍣� Not support websocket')
    }

    //杩炴帴鍙戠敓閿欒鐨勫洖璋冩柟娉�
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket杩炴帴鍙戠敓閿欒");
    };

    //杩炴帴鎴愬姛寤虹珛鐨勫洖璋冩柟娉�
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket杩炴帴鎴愬姛");
    }

    //鎺ユ敹鍒版秷鎭殑鍥炶皟鏂规硶
    websocket.onmessage = function (event) {
    	
    	var json1 = JSON.parse(event.data)
    /* 	alert(typeof(json1))
    	console.log(json1)
        setMessageInnerHTML(event.data); */
        var c = document.getelemetById('myCanvas');
        var ctx = c.getContext('2d');
        
        
        
        
        
    }

    //杩炴帴鍏抽棴鐨勫洖璋冩柟娉�
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket杩炴帴鍏抽棴");
    }

    //鐩戝惉绐楀彛鍏抽棴浜嬩欢锛屽綋绐楀彛鍏抽棴鏃讹紝涓诲姩鍘诲叧闂瓀ebsocket杩炴帴锛岄槻姝㈣繛鎺ヨ繕娌℃柇寮�灏卞叧闂獥鍙ｏ紝server绔細鎶涘紓甯搞��
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //灏嗘秷鎭樉绀哄湪缃戦〉涓�
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //鍏抽棴WebSocket杩炴帴
    function closeWebSocket() {
        websocket.close();
    }

    //鍙戦�佹秷鎭�
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
</html>