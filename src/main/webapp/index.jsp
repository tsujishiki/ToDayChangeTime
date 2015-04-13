﻿<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        div[class*="col-"]{
            padding-top:5px;
            padding-bottom:5px;
        }

        .left{
            text-align:left;
            padding-left:2px;
        }

        .right{
            text-align:right;
            padding-right:2px;
        }
    </style>

</head>
<body>

    <div class="container">
        <form action="/login" method="post" >
            <div class="row">
                <div class="col-md-4 right"><span>用户名</span></div>
                <div class="col-md-8"><input type="text" name="userName" /></div>
            </div>
            <div class="row">
                <div class="col-md-4 right"><span>密码</span></div>
                <div class="col-md-8"><input type="password" name="password"/></div>
            </div>
            <div class="row">
                <div class="col-md-2 col-md-offset-4"><button class="btn btn-default" type="submit">登陆</button></div>
            </div>
        </form>
    </div>

    <script src="resources/js/jquery-2.1.3.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
