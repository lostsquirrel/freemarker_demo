<#escape x as x?html>
<!doctype html>
<html lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <!-- Place favicon.ico in the root directory -->
        <title>
		<@tmbk type="block" name="title">
		</@tmbk>
        </title>
<!--
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/main.css">
 -->
        <@tmbk type="block" name="css">
		</@tmbk>
<!--
        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
 -->
        <@tmbk type="block" name="js">
		</@tmbk>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <!-- Add your site or application content here -->
    	<@tmbk type="block" name="content">
		</@tmbk>
		<@tmbk type="block" name="js_bottom">
		</@tmbk>

    </body>
</html>
</#escape>