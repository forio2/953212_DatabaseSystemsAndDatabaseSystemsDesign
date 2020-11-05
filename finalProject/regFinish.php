<!--when chick button 'Register' From register.php-->
<!--(Main Button)(62) to index.php-->
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
  <title>Successful!</title>
</head>
<body>
  <?php
  session_start();
  $Name = $_POST['Name'];
  $Username = $_POST['Username'];
  $Password = $_POST['Password'];
  $Email = $_POST['Email'];
  $link = mysqli_connect("localhost","root", "", "SteakJustice") or die ("Can't MySQL sever");
  if (($Name == NULL)  || ($Username == NULL) || ($Password == NULL) || ($Email == NULL)) {
    echo "<script>
      alert('Please input a data in the all row  ');
      window.location.href='register.php';
      </script>";
  } 
  else {
    $sql = "INSERT INTO Customers 
    (noCustomer, nameCustomer, usernameCustomer, passwordCustomer, emailCustomer, status)VALUES
    (Null, '$Name', '$Username', '$Password', '$Email', 'M')";
    $objQuery = mysqli_query($link, $sql);
  }
  ?>
  <!--content-->
  <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 bg-white rounded" style="width: 700px;"> <!--jumbotron, rounded, shadow and style-->
          <div class="container">
            <br>
            <h1 class="display-3">Successful!</h1>
            <br>
            <button 
              type="button" 
              onclick="window.location.href = 'index.php'" 
              class="btn btn-dark btn-lg btn-block"
            >
              Main
            </button>
            <br><br>
          </div>
        </div>
      </div>
    </div>  
  </div>
</body>
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
      padding: 280px 30px;
      text-align: center;
      position: relative;
    }
</style>
</html>