<?php
unset($_COOKIE["lecturer"]);
setcookie('lecturer', null, -1);
echo "Logout is success! Redirecting to Index";
header("Refresh: 2; url=login.php");

?>