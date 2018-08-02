//index.js
//获取应用实例
var app = getApp()
var order = ['red', 'yellow', 'blue', 'green', 'red']
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
      },
      toView: 'red',
      scrollTop: 100
    }],
    circles: [
      {
        latitude: 23.099994,
        longitude: 113.324520,
        color: "#1ecbf6",
        fillColor: "",
        radius: 500
      }
    ],
    cardList: []
  },
  onLoad: function(option){
    this.mapCtx = wx.createMapContext('myMap')
    this.moveToLocation();
  },
  moveToLocation: function () {
    this.mapCtx.moveToLocation()
  },
  enterCard: function(){
    this.data.cardList.push({
      title:"打卡成功",
      label:"打卡时间",
      desc:"打卡地点"
    });
    this.setData({
      cardList: this.data.cardList
    });
  },
  upper: function (e) {
    console.log(e)
  },
  lower: function (e) {
    console.log(e)
  },
  scroll: function (e) {
    console.log(e)
  },
  tap: function (e) {
    for (var i = 0; i < order.length; ++i) {
      if (order[i] === this.data.toView) {
        this.setData({
          toView: order[i + 1]
        })
        break
      }
    }
  },
  tapMove: function (e) {
    this.setData({
      scrollTop: this.data.scrollTop + 10
    })
  }
})
