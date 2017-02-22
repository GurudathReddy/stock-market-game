// spec.js
describe('VSM - Stock Market League', function() {

    beforeEach(function() {
        browser.get('http://10.240.13.23:8080/stockmarket/game#/form/login');
    });

    it('should have a title', function() {
        expect(browser.getTitle()).toEqual('VSM - Stock Market League');
    });

    it('should login', function() {
        var password = element(by.css('.password-field')),
            email = element(by.css('.email-field'));

        var loginButton = element(by.id('login-button-id'));
        email.sendKeys('gurudath.reddy@vsm.com');
        password.sendKeys('password');
        loginButton.click();

        expect(browser.getTitle()).toEqual('VSM - Stock Market League');
    });


});