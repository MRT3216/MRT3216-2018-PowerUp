let ux = {
    slider: {
        button: document.getElementById('foo')
    },
    startMatch: {
    	startButton: document.getElementById('startTimer')
    },
    resetMatch: {
      resetButton: document.getElementById('resetTimer')
    }
}
var elem = document.getElementById("speedRect");
elem.setAttribute("style", "display: none;");
// My event handler
// ux.foo.button.onclick = function foo(event) {
//     console.log(event); 
//     console.log(event.target);
// }

// console.log(ux.foo.button)

// ux.foo.button.addEventListener("mouseover",outputUpdate)

// ux.bar.slider.addEventListener("change",function bar(event) {
//     console.log(event);
// })

// function for speed change
function outputUpdate(speed) {
    console.log(speed)
    document.querySelector('#speed').value = speed;
    if(speed > 25){
      elem.setAttribute("style", "display: inline;");
    }
}

// function for sensitivity change
function outputUpdate2(sensitivity) {
    console.log(sensitivity)
    document.querySelector('#sensitivity').value = sensitivity;
}

// function for deadzone change
function outputUpdate3(deadzone) {
    console.log(deadzone)
    document.querySelector('#deadzone').value = deadzone;
}


// JS code for timer
document.getElementById('timer').innerHTML =
  02 + ":" + 30;

// JS Code for match start
ux.startMatch.startButton.onclick = function startTimer(){
  var presentTime = document.getElementById('timer').innerHTML;
  var timeArray = presentTime.split(/[:]+/);
  var m = timeArray[0];
  var s = checkSecond((timeArray[1] - 1));
  if(s==59){m=m-1}
  //if(m<0){alert('timer completed')}
  if(m<1 && s<1){alert("times up!")};
  document.getElementById('timer').innerHTML =
  00 + ":" + 00;
  if(m<1 && s<1){document.getElementById('timer').innerHTML = 02 + ":" + 30;};
  if(m<1 && s<1){return};
  document.getElementById('timer').innerHTML =
    m + ":" + s;
  setTimeout(startTimer, 1000);
}

function checkSecond(sec) {
  if (sec < 10 && sec >= 0) {sec = "0" + sec}; // add zero in front of numbers < 10
  if (sec < 0) {sec = "59"};
  return sec;
}





