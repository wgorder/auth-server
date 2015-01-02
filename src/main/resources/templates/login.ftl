<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/app.css"/>
</head>
<body>
<div class="container">
    <section id="form">
        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-1">
                    <div class="login-form">
                        <h2>Login to your account</h2>
                        <form role="form" action="login" method="post">
                            <input type="email" placeholder="Email" name="username"/>
                            <input type="password" placeholder="Password" name="password"/>
                            <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-default">Login</button>
                        </form>
                    </div>
                </div>
                <div class="col-sm-1">
                    <h2 class="or">OR</h2>
                </div>
                <div class="col-sm-4">
                    <div class="signup-form">
                        <h2>New User Signup!</h2>

                        <form role="form" action="register" method="post">
                            <input type="text" placeholder="First Name"/>
                            <input type="text" placeholder="Last Name"/>
                            <input type="email" placeholder="Email Address"/>
                            <input type="password" placeholder="Password"/>
                            <input type="password" placeholder="Confirm Password"/>
                            <button type="submit" class="btn btn-default">Signup</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>