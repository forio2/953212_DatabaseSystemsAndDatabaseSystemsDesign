<!--when chick button 'Save' From editProfile.php-->
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
    $sqlmain = "SELECT * FROM Customers
    WHERE usernameCustomer = '".$_SESSION['Username']."' ";
    $objQuerymain = mysqli_query($link, $sqlmain);
    $Result = mysqli_fetch_array($objQuerymain);
    if($_POST["Password"] != $_POST["ConfirmPassword"]){
        echo "<script>
            alert('Password not Match!');
            window.location.href='editProfile.php';
        </script>";
        exit();
    }
    if($_POST["Password"] == "" && $_POST["ConfirmPassword"] == "" && $_POST["Name"] == ""){
        echo "<script>
            window.location.href='profile.php';
        </script>";
        exit();
    }
    else if($_POST["Name"] == ""){
        $sql = "UPDATE Customers
        SET passwordCustomer = '".$_POST['Password']."'
        WHERE usernameCustomer = '".$_SESSION['Username']."' ";
        $objQuery = mysqli_query($link, $sql);
        if(($Result["status"] == 'A')){
            echo "<script>
            window.location.href='profileAdmin.php';
            </script>";
        }else if(($Result["status"] == 'M')){
            echo "<script>
            window.location.href='profile.php';
            </script>";
        }
    }
    else if($_POST["Password"] == "" || $_POST["ConfirmPassword"] == ""){
        $sql = "UPDATE Customers
        SET nameCustomer = '".$_POST['Name']."'
        WHERE usernameCustomer = '".$_SESSION['Username']."' ";
        $objQuery = mysqli_query($link, $sql);
        if(($Result["status"] == 'A')){
            echo "<script>
            window.location.href='profileAdmin.php';
            </script>";
        }else if(($Result["status"] == 'M')){
            echo "<script>
            window.location.href='profile.php';
            </script>";
        }
    }
    else{
        $sql = "UPDATE Customers
        SET nameCustomer = '".$_POST['Name']."', passwordCustomer = '".$_POST['Password']."'
        WHERE usernameCustomer = '".$_SESSION['Username']."' ";
        $objQuery = mysqli_query($link, $sql);
        if(($Result["status"] == 'A')){
            echo "<script>
            window.location.href='profileAdmin.php';
            </script>";
        }else if(($Result["status"] == 'M')){
            echo "<script>
            window.location.href='profile.php';
            </script>";
        }
    }
?>