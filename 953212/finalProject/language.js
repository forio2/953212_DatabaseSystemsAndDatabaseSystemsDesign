function start(){
    document.getElementById('thaiResult').style.display = 'none';
  }

function showThai(){
    document.getElementById('engResult').style.display = 'none';
    document.getElementById('thaiResult').style.display = 'block';
}

function showEng(){
    document.getElementById('engResult').style.display = 'block';
    document.getElementById('thaiResult').style.display = 'none';
}