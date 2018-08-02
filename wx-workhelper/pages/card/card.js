//index.js
//获取应用实例
var app = getApp()
Page({

  data: {
    latitude: 23.099994,
    longitude: 113.324520,
    markers: [{
      id: 1,
      latitude: 23.099994,
      longitude: 113.324520,
      name: 'T.I.T 创意园',
      label: {
        content: '创意园'
      }
    }],
    circles: [
      {
        latitude: 23.099994,
        longitude: 113.324520,
        color: "#1ecbf6",
        fillColor: "",
        radius: 500
      }
    ]
  },
  onLoad: function(option){
    this.mapCtx = wx.createMapContext('myMap')
    this.moveToLocation();
  },
  moveToLocation: function () {
    this.mapCtx.moveToLocation()
  },
})
