 <?php 
require 'ExecuteQuery.php';

 try {
 	// $a = new TrataRequisicao($_POST['message']); 
 	$a = new TrataRequisicao(); 
 }
 catch (Exception $e) { 
 	echo "Exception: ", $e->getMessage(), "\n"; 
 }

 class TrataRequisicao {

 	public $json; 

 	function __construct($json) {
 		$json_decoded = json_decode($json, true); 
 		$this->tratandoRequisicao($json_decoded); 
 	}

 	function log ($message) {
 		$fp = fopen('/home/digao/servidor/bloco1.txt', 'a+') or die ("Permission error");
 		fwrite($fp, $message . "\n");
 		fclose($fp); 
 	}

 	function tratandoRequisicao ($json) {
 		if ($json["request_type"] == "infoDB") {
 			$cc = new ExecuteQuery (); 
 			$result = $cc->makeQuery($json); 
 			exit(json_encode($result)); 
 			// $this->log ($result); 
 		} else {
 			$this->log("Entra aqui no else");
 		}

 		
 	}
 }