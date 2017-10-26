from flask import Blueprint, Flask
from flask_restful_swagger_2 import Api


def deploy(app):
    """
    :type app: Flask

    :rtype: None
    """
    def deploy_auth():
        from routes.api.auth.authentication import AdminAuth, StudentAuth

        blueprint = Blueprint('auth', __name__)

        api = Api(blueprint, api_spec_url='/api/auth', api_version=app.config['API_VER'], title=app.config['API_TITLE'] + ' Authentication API', description=app.config['API_DESC'])

        api.add_resource(AdminAuth, '/auth/admin')
        api.add_resource(StudentAuth, '/auth/student')

    def deploy_admin():
        from routes.api.admin.account.account_control import InitializeAccount
        from routes.api.admin.account.new_account import NewAccount

        from routes.api.admin.apply.afterschool import AfterSchool, AfterSchoolItem
        from routes.api.admin.apply.extension import Extension
        from routes.api.admin.apply.goingout import Goingout
        from routes.api.admin.apply.stay import Stay

        from routes.api.admin.post.faq import FAQ
        from routes.api.admin.post.notice import Notice
        from routes.api.admin.post.rule import Rule
        from routes.api.admin.post.preview import FAQPreview, NoticePreview, RulePreview

        from routes.api.admin.survey.survey import Survey, Question

        blueprint = Blueprint('admin', __name__)

        api = Api(blueprint, api_spec_url='/api/admin', api_version=app.config['API_VER'], title=app.config['API_TITLE'] + ' Admin API', description=app.config['API_DESC'])

        api.add_resource(InitializeAccount, '/admin/initialize-account')
        api.add_resource(NewAccount, '/admin/new-account')

        api.add_resource(AfterSchool, '/admin/afterschool')
        api.add_resource(AfterSchoolItem, '/admin/afterschool/item')
        api.add_resource(Extension, '/admin/extension')
        api.add_resource(Goingout, '/admin/goingout')
        api.add_resource(Stay, '/admin/stay')
        
        api.add_resource(FAQ, '/admin/faq')
        api.add_resource(Notice, '/admin/notice')
        api.add_resource(Rule, '/admin/rule')
        api.add_resource(FAQPreview, '/admin/faq/preview')
        api.add_resource(NoticePreview, '/admin/notice/preview')
        api.add_resource(RulePreview, '/admin/notice/preview')

        api.add_resource(Survey, '/admin/survey')
        api.add_resource(Question, '/admin/survey/question')

        app.register_blueprint(blueprint)

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
        from routes.api.student.post.preview import FAQPreview, NoticePreview, RulePreview

        from routes.api.student.school_data.meal import Meal

        from routes.api.student.survey.survey import SurveyList, Survey

        blueprint = Blueprint('student', __name__)
        api = Api(blueprint, api_spec_url='/api/student', api_version=app.config['API_VER'], title=app.config['API_TITLE'] + ' Student API', description=app.config['API_DESC'])

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
        api.add_resource(FAQPreview, '/faq/preview')
        api.add_resource(NoticePreview, '/notice/preview')
        api.add_resource(RulePreview, '/notice/preview')

        api.add_resource(Meal, '/meal/<date>')

        api.add_resource(SurveyList, '/survey')
        api.add_resource(Survey, '/survey/<id>')

        app.register_blueprint(blueprint)

    deploy_auth()
    deploy_admin()
    deploy_developer()
    deploy_student()
