
<?php
$conn = new mysqli("77.245.159.20", "mtlbl_bck", "Potato1234", "mtlbl_db");

/* check connection */
if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
}


?>
