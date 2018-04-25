<?php
$request_data = json_decode($this->input->post("request_data"));

if ($row = validate_user()) {
	$this->db->where("user_id", $row->user_id);
	$this->db->where("note_id", (int)($request_data->id));
	if ($this->db->delete("notes")) {
		echo json_encode([
			"status" => 0
		]);
	} else {
		echo json_encode([
			"status" => 12480
		]);
	}
} else {

	echo json_encode([
		"status" => 500
	]);
}

