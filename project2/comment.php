<?php 

$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM comment";

$data = mysqli_query($connect, $query);

class Comment {

	function Comment($ID, $acc, $accpost, $content, $date, $time, $name, $IDpost){
		$this->ID = $ID;
		$this->acc = $acc;
		$this->accpost = $accpost;
		$this->content = $content;
		$this->date = $date;
		$this->time = $time;
		$this->name = $name;
		$this->IDpost = $IDpost;
	}
}

$comment = array();

while ($row = mysqli_fetch_row($data)) {
	array_push($comment, new Comment($row['0'], $row['1'], $row['2'], $row['3'], $row['4'], $row['5'], $row['6'], $row['7']));
}

echo json_encode($comment);

 ?>