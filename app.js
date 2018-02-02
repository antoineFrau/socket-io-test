var { spawnSync } = require( 'child_process' ),
	// Permet de compile le fichier C
    compile = spawnSync( 'gcc', [ ' main.c', ' -o', 'executablename' ] ),
    // Permet d'executer le code du C
    execute = spawnSync( './executablename');

var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

//Quand on se co au site on affiche le fichier index.html
app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(socket){
  console.log("new user");
  //Quand le button du web a été cliqué
  socket.on('buttonfromweb', function(msg){
    execute = spawnSync( './executablename');
    console.log(`${execute.stdout.toString()}`);
    //On envoie un notif (message) au téléphone qui écoute que s'il reçoi un message avec 
    //comme titre sendtophone
    io.emit('sendtophone', `${execute.stdout.toString()}`);
  });
  //Quand le button du phone a été cliqué
  socket.on('buttonfromandroid', function(msg){
    execute = spawnSync( './executablename');
    console.log(`${execute.stdout.toString()}`);
    //Idem que pour le Phone
    io.emit('sendtoweb', `${execute.stdout.toString()}`);
  });
});

http.listen(8080, function(){
  console.log('listening on *:8080');
});