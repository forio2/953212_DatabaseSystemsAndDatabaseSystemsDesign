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
$sql = "SELECT sum(Orders.quantity*price) as total 
FROM Customers INNER JOIN Orders
ON Customers.noCustomer = Orders.noCustomer
INNER JOIN  Menu
ON Orders.noMenu = Menu.noMenu
WHERE Customers.noCustomer = '" . $_SESSION['ID'] . "'
group by Customers.nameCustomer;";
$objQuery = mysqli_query($link, $sql);
$Result = mysqli_fetch_array($objQuery);

$sql2 = "SELECT nameMeal, Orders.noOrder as orderID, price ,sum(Orders.quantity*price)  ,sum(Orders.quantity)  
FROM Customers INNER JOIN Orders
ON Customers.noCustomer = Orders.noCustomer
INNER JOIN  Menu
ON Orders.noMenu = Menu.noMenu
WHERE Customers.noCustomer = '" . $_SESSION['ID'] . "'
group by nameMeal;";
$objQuery2 = mysqli_query($link, $sql2);
?>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>

<body>
    <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
        <div class="col-auto margin">
        <!--margin-->
        <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 rounded" style="width: 700px;">
          <!--jumbotron, rounded and shadow -->
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1>Receipt</h1>
                    </div>
                    <div class="col-md-12">
                        <form action="receiptFinish.php" method="POST">
                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">
                                            Meal name
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
                                            <?php echo $Result2["sum(Orders.quantity)"]; ?>
                                        </td>
                                        <td>
                                            <?php echo $Result2["sum(Orders.quantity*price)"]; ?>
                                        </td>
                                    </tr>
                                </tbody>
                                <?php
                                }
                                ?>
                            </table> 
                            
                            <table class="table">
                                <tr class="bg-dark">
                                    <td class="text-white">
                                        <?php
                                        if(!$Result){
                                            echo "No Order";
                                        }
                                        else{
                                            echo $Result["total"];
                                            $_SESSION['total'] = $Result['total'];
                                        }
                                         ?>
                                    </td>        
                                </tr>
                            </table> 
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-lg " style="width: 300px">Submit</button>
                                <button type="button" onclick="window.location.href = 'profile.php'" class="btn btn-info btn-lg" style="width: 300px">Back</button>
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
      margin-top: 250px;
      margin-bottom: 210px;
    }
  </style>
</html>