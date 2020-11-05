<!--when chick button 'Edit' From profile.php or profileAdmin.php-->
<!--(Save Button)(60) to saveProfile.php-->
<!--(Back Button)(80) back to profile.php(status = M) or profileAdmin.php(status = A)-->
<?php
    session_start();
    if($_SESSION['Username'] == ""){
        echo "<script>
            alert('Please Login!');
            window.location.href='login.php';
        </script>";
        exit();
    }
    $link = mysqli_connect("localhost","root", "", "SteakJustice") or die ("Can't MySQL sever");
    $sql = "SELECT * FROM Customers WHERE usernameCustomer = '".$_SESSION['Username']."' ";
    $objQuery = mysqli_query($link, $sql);
    $Result = mysqli_fetch_array($objQuery);
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="style.css">

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
      padding: 180px 30px;
      text-align: center;
      position: relative;
    }
  </style>
</head>
<body>
  <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 rounded" style="width: 700px;"> <!--jumbotron, rounded and shadow -->
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <h1>Edit</h1>
                <br><br>
              </div>
              <div class="col-md-12">
                <!--Save Profile-->
                <form action="saveProfile.php" method="POST">
                  <div class="form-group">
                    <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="<?php echo $Result["usernameCustomer"] ?>">
                  </div>
                  <div class="form-group">
                    <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="<?php echo $Result["emailCustomer"] ?>">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" placeholder="change name"  name="Name">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control" placeholder="new password" name="Password">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control" placeholder="confirm password" name="ConfirmPassword">
                  </div>
                  <div class="form-group">
                    <button type="submit" class="btn btn-dark">Save</button>
                  <!--Back to profile.php or profileAdmin.php-->
                  <?php
                  if(($Result["status"] == 'A')){
                  ?>
                    <button type="button" onclick="window.location.href = 'profileAdmin.php'" class="btn btn-secondary">Back</button>
                  <?php
                  }else if(($Result["status"] == 'M')){
                  ?>
                    <button type="button" onclick="window.location.href = 'profile.php'" class="btn btn-secondary">Back</button>
                  <?php
                  }
                  ?>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>