  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyA2negqMNjO1-8LfJb342yJNDlV2x26MHQ",
    authDomain: "bpos-calladmin-service.firebaseapp.com",
    databaseURL: "https://bpos-calladmin-service.firebaseio.com",
    storageBucket: "bpos-calladmin-service.appspot.com",
    messagingSenderId: "885095853959"
  };
  firebase.initializeApp(config);

  var words = new Object();
  var currentWord;
  var searchResult;
  var state = false;

  var commentsRef = firebase.database().ref('words/');
  commentsRef.on('child_added', function(data) {
    var key = data.key;
    data = data.val();
    addElement(key, data);
  });

  commentsRef.on('child_changed', function(data) {
    var key = data.key;
    data = data.val();
    setElement(key, data);
    if($('#title').text()==key){
      $('#content').html(data.content);
    }
  });

  commentsRef.on('child_removed', function(data) {
    var key = data.key;
    data = data.val();
    deleteElement(key, data);
  });

  function addElement(key, data){
    words[key] = data;
    var description = data.content+"";
    if(description.length>30) description = description.substring(0,30)+'...';
    addCommentElement(key,description);
  }

  function setElement(key, data){
    words[key] = data;
    var description = data.content+"";
    if(description.length>30) description = description.substring(0,30)+'...';
    $('#'+key+'-des').text(description);
  }

  function deleteElement(key, data){
    delete words[key];
  }

  function addCommentElement(title, description){
    $("#section").append('<li href="#messaging" class="message" onclick="view(\''+title+'\')">'
            +'<a data-toggle="collapse" href="#" aria-expanded="false" aria-controls="collapseMessaging">'
              +'<div class="message">'
                +'<div class="content">'
                  +'<div id="'+title+'" class="title">'+title+'</div>'
                  +'<div id="'+title+'-des" class="description">'+description+'</div>'
                +'</div>'
              +'</div>'
            +'</a>'
          +'</li>');
  }

  function view(key){
    currentWord = key;
    $('#title').text(key);
    $('#content').html(words[key].content);
    if(state){
      state = false;
      change_state();
    }
  }

  function update(){
    if(currentWord==null){
      alert('선택된 글이 없습니다.');
      return;
    }
    if(state&&confirm('이전 글이 삭제됩니다. 적용하시겠습니까?')){
   var data = words[currentWord];
   data.content = $('#message-box-area_ifr').contents().find('body').html();
    firebase.database().ref('words/'+currentWord).set(words[currentWord]);
    state=false;
      change_state();
    }else if(!state){
      state=true;
      change_state();
    }
  }

  function add(){
    var text = $('#add-item').val();
    console.log(text);
    $('#add-item').val('');
    firebase.database().ref('words/'+text).set({
      content: ''
    });
  }

  function change_state(){
    if(state){
    $('#normal-content').css( "display", "none" );
    $('#editor-content').css( "display", "block" );
    $('#message-box-area_ifr').contents().find('body').html(words[currentWord].content);
    }else{
    $('#normal-content').css( "display", "block" );
    $('#editor-content').css( "display", "none" );
    }
  }
  
