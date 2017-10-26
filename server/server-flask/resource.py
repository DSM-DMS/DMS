from flask import Blueprint, Flask
from flask_restful_swagger_2 import Api


def deploy(app):
    """
    :type app: Flask

    :rtype: None
    """
    def deploy_admin():
        from routes.api.admin.account.account_control import InitializeAccount
        from routes.api.admin.account.new_account import NewAccount
        from routes.api.admin.post.faq import FAQ
        from routes.api.admin.post.notice import Notice
        from routes.api.admin.post.rule import Rule
        from routes.api.admin.survey.survey import Survey, Question

        blueprint = Blueprint('admin', __name__)
        admin_api = Api(blueprint, api_spec_url='/api/admin', api_version=app.config['API_VER'], title=app.config['API_TITLE'] + ' Admin API', description=app.config['API_DESC'])

        admin_api.add_resource(InitializeAccount, '/admin/initialize-account')
        admin_api.add_resource(NewAccount, '/admin/new-account')

        admin_api.add_resource(FAQ, '/admin/faq')
        admin_api.add_resource(Notice, '/admin/notice')
        admin_api.add_resource(Rule, '/admin/rule')

        admin_api.add_resource(Survey, '/admin/survey')
        admin_api.add_resource(Question, '/admin/survey/question')

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

        from routes.api.student.school_data.meal import Meal

        from routes.api.student.survey.survey import SurveyList, Survey

        blueprint = Blueprint('student', __name__)
        student_api = Api(blueprint, api_spec_url='/api/student', api_version=app.config['API_VER'], title=app.config['API_TITLE'] + ' Student API', description=app.config['API_DESC'])

        student_api.add_resource(ChangePW, '/change/pw')
        student_api.add_resource(ChangeNumber, '/change/number')
        student_api.add_resource(UUIDVerification, '/uuid-verify')
        student_api.add_resource(Signup, '/signup')

        student_api.add_resource(Extension, '/extension')
        student_api.add_resource(Goingout, '/goingout')
        student_api.add_resource(Stay, '/stay')

        student_api.add_resource(BugReport, '/bug-report')

        student_api.add_resource(FAQList, '/faq')
        student_api.add_resource(FAQ, '/faq/<id>')
        student_api.add_resource(NoticeList, '/notice')
        student_api.add_resource(Notice, '/notice/<id>')
        student_api.add_resource(RuleList, '/rule')
        student_api.add_resource(Rule, '/rule/<id>')

        student_api.add_resource(Meal, '/meal/<date>')

        student_api.add_resource(SurveyList, '/survey')
        student_api.add_resource(Survey, '/survey/<id>')

        app.register_blueprint(blueprint)

    deploy_admin()
    deploy_developer()
    deploy_student()
