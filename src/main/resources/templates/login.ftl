<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Login</title>
</head>
<body>
<h3>Login page</h3>
<form action='/login' method='POST'>
    <table>
        <tr>
            <td>User Name:</td>
            <td>
                <input type='text' name='username' value=''>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type='password' name='password' />
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit" type="submit" value="Login" />
            </td>
        </tr>
    </table>
</form>
<p>Default UserName/Password: admin/123456</p>
</body>
</html>