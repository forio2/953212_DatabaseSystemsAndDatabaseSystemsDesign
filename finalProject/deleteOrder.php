<?php

session_start();
$link = mysqli_connect("localhost", "root", "", "SteakJustice") or die("Can't MySQL sever");
$sql = "SELECT * FROM Customers 
WHERE noCustomer = '" . $_SESSION['ID'] . "' ";
$objOrders = mysqli_query($link, $sql);
$orderResult = mysqli_fetch_array($objOrders);


$sql2 = "DELETE  FROM  Orders WHERE noOrder = '".$_POST['deleteOrder']."' ";
$objQuery = mysqli_query($link, $sql2) or die(mysqli_error($link));
echo "<script>
            window.location.href='profile.php';
    </script>";

?>
