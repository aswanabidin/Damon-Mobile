<?php
      $server   = "localhost";
      $user     = "root";
      $password = "enter";
      $database = "db_danamon";

      $connect = mysql_connect($server, $user, $password) or die ("Koneksi Gagal");
      mysql_select_db($database) or die ("Database belum siap");
?>
