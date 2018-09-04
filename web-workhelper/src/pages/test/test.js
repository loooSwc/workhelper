
export default {
  data () {
    return {
      loginWay: false, //  登录方式，默认短信登录
      showPassword: false, // 是否显示密码
      phoneNumber: null, // 电话号码
      mobileCode: null, // 短信验证码
      validate_token: null, // 获取短信时返回的验证值，登录时需要
      computedTime: 0, // 倒数记时
      userInfo: null, // 获取到的用户信息
      userAccount: null, // 用户名
      passWord: null, // 密码
      captchaCodeImg: null, // 验证码地址
      codeNumber: null, // 验证码
      showAlert: false, // 显示提示组件
      alertText: null // 提示的内容
    }
  }
}
