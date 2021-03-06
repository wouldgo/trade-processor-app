/*global angular*/

(function withAngular(angular) {
  'use strict';

  angular.module('marketTrade.factories.radomColor', [])

  .service('RandomColor', [function randomColorFactory() {

    var RColor = function RColor() {

      this.hue = Math.random();
      this.goldenRatio = 0.618033988749895;
      this.hexwidth = 2;
    };

    RColor.prototype.hsvToRgb = function hsvToRgb(h, s, v) {

      var hI = Math.floor(h * 6)
        , f = h * 6 - hI
        , p = v * (1 - s)
        , q = v * (1 - f * s)
        , t = v * (1 - (1 - f) * s)
        , r = 255
        , g = 255
        , b = 255;
      switch (hI) {

        case 0:

          r = v;
          g = t;
          b = p;
          break;
        case 1:

          r = q;
          g = v;
          b = p;
          break;
        case 2:

          r = p;
          g = v;
          b = t;
          break;
        case 3:

          r = p;
          g = q;
          b = v;
          break;
        case 4:

          r = t;
          g = p;
          b = v;
          break;
        default:

          r = v;
          g = p;
          b = q;
          break;
      }
      return [Math.floor(r * 256), Math.floor(g * 256), Math.floor(b * 256)];
    };

    RColor.prototype.padHex = function padHex(str) {

      if (str.length > this.hexwidth) {

        return str;
      }
      return new Array(this.hexwidth - str.length + 1).join('0') + str;
    };

    RColor.prototype.get = function get(hex, saturation, value) {

      this.hue += this.goldenRatio;
      this.hue %= 1;
      if (typeof saturation !== 'number') {

        saturation = 0.5;
      }

      if (typeof value !== 'number') {

        value = 0.95;
      }
      var rgb = this.hsvToRgb(this.hue, saturation, value);
      if (hex) {

        return '#' + this.padHex(rgb[0].toString(16))
              + this.padHex(rgb[1].toString(16))
              + this.padHex(rgb[2].toString(16));
      }

      return rgb;
    };

    return new RColor();
  }]);
}(angular));
