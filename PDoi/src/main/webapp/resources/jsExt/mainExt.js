
let size = localStorage.getItem('font');
if (size) {
	document.querySelector('body').style.fontSize = size + 'px';
	document.querySelector('.wrapper').style.fontSize = size + 'px';
}

let handicap = localStorage.getItem('handicap');
if(handicap){
	document.querySelector('body').classList.toggle(handicap);
}

document.querySelector('.mobile-menu-handle').onclick = function() {
  document.querySelector('.menu').classList.toggle('menu-opened');
};

document.addEventListener('click',  function(event) {
  let ref = document.querySelector('.menu');
  if (!ref.contains(event.target) && ref.classList.contains('menu-opened')) {
    ref.classList.toggle('menu-opened');
  }
});

function font(increase) {
  let body =  document.querySelector('body');
  let wrapper =  document.querySelector('.wrapper');
  let size = window.getComputedStyle(body).getPropertyValue('font-size').replace('px', '');
  let max = 20;
  let min = 10;
  if (increase) {
    size <= max ? size++ : '';
  } else {
    size >= min ? size-- : '';
  }
  body.style.fontSize = size + 'px';
  wrapper.style.fontSize = size + 'px';
  localStorage.setItem('font', size);
}

function resetFont() {
	document.querySelector('body').style.fontSize = '12pt';
	document.querySelector('.wrapper').style.fontSize = '12px';
	localStorage.removeItem('font');
}

function colorBlind() {
  if(document.querySelector('body').classList.toggle('color-blind')){
	  localStorage.setItem('handicap', 'color-blind');
  } else {
	  localStorage.removeItem('handicap');
  }
}
