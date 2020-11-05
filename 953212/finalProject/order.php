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
      text-align: center;
      position: relative;
    }

    .margin{
      margin-top: 310px;
      margin-bottom: 300px;
    }

  </style>
</head>
<body>
  <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto margin"> <!--margin-->
      <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 rounded" style="width: 700px;"> <!--jumbotron, rounded and shadow -->
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <input type="text" readonly class="form-control-plaintext" value="<?php echo $_POST['orderName'] ?>"></input>
              </div>
              <div class="col-md-12">
                <form action="orderFinish.php" method="POST">
                    <div class="form-group">
                        <input type="hidden" readonly class="form-control-plaintext" value="<?php echo $_POST['orderValue'] ?>" name="addName">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Quantity" name="Qty">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-lg " style="width: 300px">Order</button>
                        <button type="button" onclick="window.location.href = 'main.php'" class="btn btn-secondary btn-lg" style="width: 300px">Back</button>  
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