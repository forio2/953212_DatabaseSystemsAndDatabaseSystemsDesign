<?php
    session_start();
    if($_SESSION['Username'] == ""){
        echo "Please Login!";
        exit();
    }
    $link = mysqli_connect("localhost","root", "", "SteakJustice") or die ("Can't MySQL sever");
    $sql = "SELECT * FROM Customers WHERE usernameCustomer = '".$_SESSION['Username']."' ";
    $objQuery = mysqli_query($link, $sql);
    $Result = mysqli_fetch_array($objQuery);
?>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Hello, world!</title>
  </head>
  <body>
    <!-- Login button-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a href="index.php"><span class="navbar-brand mb-0 h1">Steak Justice</span></a> 
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
        </div>
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <button onclick="window.location.href = 'profileAdmin.php'" type="button" class="btn btn-outline-dark btn-sm">Adjust</button>
            <button onclick="window.location.href = 'logout.php'" type="button" class="btn btn-outline-dark btn-sm">Logout</button>
        </li>
      </ul>
    </nav>
    <!-- image and text-->
    <!-- work with style.css(.img-fluid, .logo-wrapper)-->
    <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid">
          <div class="container">
            <h1>Welcome Admin!</h1>
            <p>
              Lorem ipsum dolor sit amet, consectetur 
              adipiscing elit,<br> sed do eiusmod tempor 
              incididunt ut labore et dolore magna<br> aliqua.
              Ut enim ad minim veniam, quis nostrud
              exercitation<br> ullamco laboris nisi ut aliquip 
              ex ea commodo consequat. 
            </p>
          </div>
        </div>
      </div>
    </div>  
    </div>
    <style>
      .img-fluid{
        height: max-content;
        width: auto;
        background-image: linear-gradient(150deg, rgba(89, 2, 2, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/s.jpg");
        background-position: 0%, 0%, 50%, 50%;
        background-attachment: scroll, fixed;
        background-size: auto, cover;
      }
      .logo-wrapper {
        font-family: 'Raleway', sans-serif;
        padding: 240px 30px;
        text-align:center;
        position: relative;
      }
    </style>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
  </body>
</html>