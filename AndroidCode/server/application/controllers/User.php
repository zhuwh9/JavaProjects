<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User extends CI_Controller {

	public function login()
	{
		header("Content-Type: application/json");
		$this->load->view('user/login');
	}
	public function register()
	{
		header("Content-Type: application/json");
		$this->load->view('user/register');
	}
	public function essay()
	{
		header("Content-Type: application/json");
		$this->load->view('user/essay');
	}
}
