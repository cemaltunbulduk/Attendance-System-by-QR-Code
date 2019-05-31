
<?php
$conn = new mysqli("server_ip", "mtlbl_bck", "password", "mtlbl_db");

/* check connection */
if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
}


?>
