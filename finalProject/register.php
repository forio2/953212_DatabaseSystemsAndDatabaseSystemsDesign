<!--when chick button 'Apply!' From index.php-->
<!--(Register Button)(42) to regFinish.php-->
<!--(Back Button)(43) back to index.php-->
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="style.css">
  <title>Register</title>
</head>
<body>
  <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 rounded" style="width: 700px;"> <!--jumbotron, rounded, shadow and style-->
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <h1>REGISTER</h1>
                <br><br>
              </div>
              <!--Register form-->
              <div class="col-md-12">
                <form action="regFinish.php" method="POST">
                  <div class="form-group">
                    <input type="text" class="form-control" placeholder="Enter Name" name="Name">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" placeholder="Enter Username" name="Username">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control" placeholder="Enter Password" name="Password">
                  </div>
                  <div class="form-group">
                    <input type="email" class="form-control" placeholder="Enter Email" name="Email">
                  </div>
                  <div class="form-group">
                    <!--Register button and Back button-->
                    <button type="submit" class="btn btn-dark">Register</button>
                    <button type="button" onclick="window.location.href = 'index.php'" class="btn btn-secondary">Back</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <style>
    .img-fluid {
      height: max-content;
      width: auto;
      background-image: linear-gradient(150deg, rgba(89, 2, 2, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/s.jpg");
      background-position: 0%, 0%, 50%, 50%;
      background-attachment: scroll, fixed;
      background-size: auto, cover;
    }

    .logo-wrapper {

      font-family: 'Raleway', sans-serif;
      padding: 220px 30px;
      text-align: center;
      position: relative;
    }

    #status{
      text-align: left;
    }
  </style>
</body>
</html>