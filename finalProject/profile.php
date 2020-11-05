<?php
session_start();
if ($_SESSION['Username'] == "") {
  echo "<script>
          alert('Please Login!');
          window.location.href='login.php';
        </script>";
  exit();
}
$link = mysqli_connect("localhost", "root", "", "SteakJustice") or die("Can't MySQL sever");
$sql = "SELECT * FROM Customers WHERE usernameCustomer = '" . $_SESSION['Username'] . "' ";
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

    .margin {
      margin-top: 170px;
    }
  </style>
</head>

<body>
  <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto margin">
        <!--margin-->
        <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 rounded" style="width: 700px;">
          <!--jumbotron, rounded and shadow -->
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <h1>Profile</h1>
              </div>
              <div class="col-md-12">
                <form action="editProfile.php" method="POST">
                  <div class="form-group">
                    <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="Name: <?php echo $Result["nameCustomer"] ?>">
                  </div>
                  <div class="form-group">
                    <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="ID: <?php echo $Result["usernameCustomer"] ?>">
                  </div>
                  <div class="form-group">
                    <input type="password" readonly class="form-control-plaintext" id="staticEmail" value="<?php echo $Result["passwordCustomer"] ?>">
                  </div>
                  <div class="form-group">
                    <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="Email: <?php echo $Result["emailCustomer"] ?>">
                  </div>
                  <div class="form-group">
                    <button type="submit" class="btn btn-dark btn-lg " style="width: 300px">Edit</button>
                    <!--Back to main.php or mainAdmin.php-->
                    <?php
                    if(($Result["status"] == 'A')){
                    ?>
                      <button type="button" onclick="window.location.href = 'mainAdmin.php'" class="btn btn-secondary btn-lg" style="width: 300px">Back</button>
                    <?php
                    }else if(($Result["status"] == 'M')){
                    ?>
                      <button type="button" onclick="window.location.href = 'main.php'" class="btn btn-secondary btn-lg" style="width: 300px">Back</button>
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
    <!--show history-->
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 rounded" style="width: 700px;"> <!--jumbotron, rounded and shadow -->
          <div class="container">
            <?php
            $table = "SELECT nameCustomer ,nameMeal, Orders.noOrder as orderID, price ,sum(Orders.quantity*price)  ,sum(Orders.quantity)  
            FROM Customers INNER JOIN Orders
            ON Customers.noCustomer = Orders.noCustomer
            INNER JOIN  Menu
            ON Orders.noMenu = Menu.noMenu
            WHERE Customers.noCustomer = '" . $_SESSION['ID'] . "'
            group by Customers.nameCustomer, noOrder;";
            $objQuery2 = mysqli_query($link, $table) or die( mysqli_error($link));
            ?>
            <table class="table">
              <thead class="thead-dark">
              <tr>
                <th scope="col">
                  Meal name
                </th>
                <th scope="col" >
                  Price
                </th>
                <th scope="col">
                  Quantity
                </th>
                <th scope="col">
                  Payment
                </th>
                <th scope="col"></th>
              </tr>
              </thead>
              <?php
              while ($Result2 = mysqli_fetch_array($objQuery2)) {
                ?>
                <tbody>
                  <tr>
                    <td>
                      <?php echo $Result2["nameMeal"]; ?>
                    </td>
                    <td>
                      <?php echo $Result2["price"]; ?>
                    </td>
                    <td>
                      <?php echo $Result2["sum(Orders.quantity)"]; ?>
                    </td>
                    <td>
                      <?php echo $Result2["sum(Orders.quantity*price)"]; ?>
                    </td>
                    <td>
                    <!--delete order-->
                    <form id="order" action="deleteOrder.php" method="POST">
                      <button 
                        type="submit"
                        name="deleteOrder"
                        onclick="document.getElementById('order');" 
                        class="btn btn-danger btn" 
                        style="width: 100px"
                        value="<?php echo $Result2["orderID"]; ?>"
                        >
                        Delete
                      </button>
                    </form>
                    </td>
                  </tr>
                  <?php  
              }
                  ?>
                </tbody>
            </table>
            <!--Total-->
            <?php
            $table2 = "SELECT sum(Orders.quantity*price) as total 
            FROM Customers INNER JOIN Orders
            ON Customers.noCustomer = Orders.noCustomer
            INNER JOIN  Menu
            ON Orders.noMenu = Menu.noMenu
            WHERE Customers.noCustomer = '" . $_SESSION['ID'] . "'
            group by Customers.nameCustomer;";
            $objQuery3 = mysqli_query($link, $table2) or die(mysqli_error($link)) ;
            ?>
            <table class="table">
              <thead class="bg-dark">
                <tr>
                  <th scope="col" class="text-white ">
                    Total
                  </th>
                </tr>
              </thead>
              <?php
              while ($Result3 = mysqli_fetch_array($objQuery3)) {
              ?>
                <tr class="bg-light">
                  <td>
                    <?php echo $Result3["total"]; ?>
                  </td>        
                </tr>
              <?php
              }
              ?>
            </table>
          </div>
          <form id="goReceipt" action="receipt.php" method="POST">
            <button 
              onclick="document.getElementById('goReceipt')" 
              type="submit" 
              class="btn btn-lg btn-info">Purchase</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</body>

</html>