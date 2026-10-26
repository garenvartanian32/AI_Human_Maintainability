(function () {
print(readline().split('+').map(function (x) { return parseInt(x);}).sort(function (a, b) { return a - b; }).join('+'));
}).call(this);