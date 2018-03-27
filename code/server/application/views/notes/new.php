<?php
$request_data = json_decode($this->input->post("request_data"));

if ($row = validate_user()) {

	$data = [
		"text"       => $request_data->text,
		"user_id" => $row->user_id,
		"category_id" => 0,
		"title" => $request_data->title,
		"created" => date('Y-m-d H:i:s'),
		"modify" => date('Y-m-d H:i:s'),
		"encryption" => 0
	];
	if ($this->db->insert("notes", $data)){

		echo json_encode([
			"status" => 0,
			"id" => $this->db->insert_id()
		]);
	} else {
		echo json_encode([
			"status" => 500
		]);
	}

} else {

	echo json_encode([
		"status" => 500
	]);
}

