import pymysql
from pymysql.cursors import DictCursor

import config

from db.models.account import StudentModel
from db.models.apply import GoingoutApplyModel, StayApplyModel
from db.models.post import FAQModel, NoticeModel, RuleModel

connection = pymysql.connect(
    host='localhost',
    user='root',
    password=config.MYSQL_PW,
    db='dsm_dms',
    charset='utf8'
)


def migrate_apply(id, uuid):
    student = StudentModel.objects(id=id).first()

    cursor = connection.cursor(DictCursor)

    cursor.execute("SELECT * FROM goingout_apply WHERE uid='{0}'".format(uuid))
    result = cursor.fetchall()[0]
    goingout_apply = GoingoutApplyModel(on_saturday=result['sat'], on_sunday=result['sun'])

    cursor.execute("SELECT * FROM stay_apply WHERE uid='{0}'".format(uuid))
    result = cursor.fetchall()[0]
    stay_apply = StayApplyModel(value=result['value'])

    student.update(goingout_apply=goingout_apply, stay_apply=stay_apply)


def migrate_posts():
    FAQModel.drop_collection()
    NoticeModel.drop_collection()
    RuleModel.drop_collection()

    cursor = connection.cursor(DictCursor)

    cursor.execute('SELECT * FROM faq')
    faqs = cursor.fetchall()
    for faq in faqs:
        FAQModel(title=faq['title'], content=faq['content']).save()

    cursor.execute('SELECT * FROM notice')
    notices = cursor.fetchall()
    for notice in notices:
        NoticeModel(title=notice['title'], content=notice['content']).save()

    cursor.execute('SELECT * FROM rule')
    rules = cursor.fetchall()
    for rule in rules:
        RuleModel(title=rule['title'], content=rule['content']).save()


if __name__ == '__main__':
    migrate_posts()
