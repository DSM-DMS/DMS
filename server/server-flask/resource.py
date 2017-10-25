from flask_restful_swagger_2 import Api


def deploy(app):
    """
    :type app: Flask

    :rtype: None
    """
    api = Api(app, api_version=app.config['API_VER'], title=app.config['API_TITLE'], description=app.config['API_DESC'])

    def deploy_admin():
        from routes.api.admin.account.account_control import InitializeAccount
        from routes.api.admin.account.new_account import NewAccount
        from routes.api.admin.post.faq import FAQ
        from routes.api.admin.post.notice import Notice
        from routes.api.admin.post.rule import Rule
        from routes.api.admin.survey.survey import Survey, Question

        api.add_resource(InitializeAccount, '/admin/initialize-account')
        api.add_resource(NewAccount, '/admin/new-account')

        api.add_resource(FAQ, '/admin/faq')
        api.add_resource(Notice, '/admin/notice')
        api.add_resource(Rule, '/admin/rule')

        api.add_resource(Survey, '/admin/survey')
        api.add_resource(Question, '/admin/survey/question')

    def deploy_developer():
        pass

    def deploy_student():
        from routes.api.student.account.after_signup import ChangePW, ChangeNumber
        from routes.api.student.account.signup import UUIDVerification, Signup

        from routes.api.student.apply.extension import Extension
        from routes.api.student.apply.goingout import Goingout
        from routes.api.student.apply.stay import Stay

        from routes.api.student.dms_system.dms_system import BugReport

        from routes.api.student.post.faq import FAQList, FAQ
        from routes.api.student.post.notice import NoticeList, Notice
        from routes.api.student.post.rule import RuleList, Rule

        from routes.api.student.school_data.meal import Meal

        from routes.api.student.survey.survey import SurveyList, Survey

        api.add_resource(ChangePW, '/change/pw')
        api.add_resource(ChangeNumber, '/change/number')
        api.add_resource(UUIDVerification, '/uuid-verify')
        api.add_resource(Signup, '/signup')

        api.add_resource(Extension, '/extension')
        api.add_resource(Goingout, '/goingout')
        api.add_resource(Stay, '/stay')

        api.add_resource(BugReport, '/bug-report')

        api.add_resource(FAQList, '/faq')
        api.add_resource(FAQ, '/faq/<id>')
        api.add_resource(NoticeList, '/notice')
        api.add_resource(Notice, '/notice/<id>')
        api.add_resource(RuleList, '/rule')
        api.add_resource(Rule, '/rule/<id>')

        api.add_resource(Meal, '/meal/<date>')

        api.add_resource(SurveyList, '/survey')
        api.add_resource(Survey, '/survey/<id>')

    deploy_admin()
    deploy_developer()
    deploy_student()
