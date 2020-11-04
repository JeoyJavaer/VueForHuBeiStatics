export function formatTimeStamp(timeStamp) {
  var date = new Date();
  date.setTime(timeStamp * 1000);
  var y = date.getFullYear();
  var m = date.getMonth() + 1;
  m = m < 10 ? ('0' + m) : m;
  var d = date.getDate();
  d = d < 10 ? ('0' + d) : d;
  var h = date.getHours();
  h = h < 10 ? ('0' + h) : h;
  var minute = date.getMinutes();
  var second = date.getSeconds();
  minute = minute < 10 ? ('0' + minute) : minute;
  second = second < 10 ? ('0' + second) : second;
  return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;

}

export function getDayStr(date) {

  const newDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
  return newDate;
}

export function getTimeStr(date) {

  const newDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' '
    + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
  return newDate;
}