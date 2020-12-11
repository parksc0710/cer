<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="style-1">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	
	<link rel="icon" href="/resources/favicon.ico">	
		
	<link rel="stylesheet" href="/resources/assets/bootstrap/css/bootstrap.min.css">
		
	<link rel="stylesheet" href="/resources/assets/css/uikit.css" />
	
	<script src="/resources/assets/js/jquery-latest.min.js"></script>
	<script type="text/javascript" src="/resources/assets/js/uikit.js"></script>
	

    <title>Register</title>

  </head>

  <body class="tile-1-bg">
    <!-- Empty Block (use .abs-filler to fill page)
    ================================================== -->
    <div class="empty-block abs-filler">
      <!-- Vcenter -->
      <div class="vcenter">
        <div class="vcenter-this">
          <!-- Container -->
          <div class="container">
            <!-- Form Panel -->
            <div class="form-panel width-40pc width-100pc-xs hcenter">
              <header>Sign up</header>
              <fieldset>
              <form action="../member/signup.do" method="post">
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-user"></i></div>
                    <input type="text" class="form-control" placeholder="ID" name="user_id">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                    <input type="password" class="form-control" placeholder="Password" name="user_pw">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-lock"></i></div>
                    <input type="password" class="form-control" placeholder="Confirm Password">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-user"></i></div>
                    <input type="email" class="form-control" placeholder="Email Address" name="user_email">
                  </div>
                </div>
                <div class="form-group">
                  <label class="checkbox-inline"><input type="checkbox" value="">I agree to the <a class="href">Terms</a> of Use</label>
                </div>
                <button class="btn btn-default btn-lg btn-block">sign up</button>
              </form>
              </fieldset>
            </div>
            <!-- /Form Panel -->
          </div>
          <!-- /Container -->
        </div>
        <!-- /Vcenter this -->
      </div>
      <!-- /Vcenter -->
    </div>
    <!-- /Empty Block
    ================================================== -->
    
  </body>
</html>
