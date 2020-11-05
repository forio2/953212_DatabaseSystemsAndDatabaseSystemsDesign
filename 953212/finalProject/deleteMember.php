<?php

session_start();

$link = mysqli_connect("localhost", "root", "", "SteakJustice") or die("Can't MySQL sever");
$sql = "SELECT * FROM Customers WHERE noCustomer = '" . $_SESSION['ID'] . "' ";
$deleteQuery = mysqli_query($link, $sql);

$sql2 = "DELETE  FROM  Customers WHERE noCustomer = '".$_POST['deleteMember']."' ";
$objQuery = mysqli_query($link, $sql2) or die(mysqli_error($link));
    echo "<script>
    window.location.href='profileAdmin.php';
    </script>";
?>
