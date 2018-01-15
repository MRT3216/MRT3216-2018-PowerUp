let ux = {
    slider: {
        button: document.getElementById('foo')
    },
}
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
}

// function for sensitivity change
function outputUpdate2(sense) {
    console.log(sense)
    document.querySelector('#sense').value = sense;
}

// function for deadspace change
function outputUpdate3(dead) {
    console.log(dead)
    document.querySelector('#dead').value = dead;
}

document.getElementById('timer').innerHTML =
  02 + ":" + 30;
startTimer();

function startTimer() {
  var presentTime = document.getElementById('timer').innerHTML;
  var timeArray = presentTime.split(/[:]+/);
  var m = timeArray[0];
  var s = checkSecond((timeArray[1] - 1));
  if(s==59){m=m-1}
  //if(m<0){alert('timer completed')}
  
  document.getElementById('timer').innerHTML =
    m + ":" + s;
  setTimeout(startTimer, 1000);
}

function checkSecond(sec) {
  if (sec < 10 && sec >= 0) {sec = "0" + sec}; // add zero in front of numbers < 10
  if (sec < 0) {sec = "59"};
  return sec;
}
