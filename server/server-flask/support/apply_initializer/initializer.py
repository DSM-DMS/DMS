import datetime
import time

from app.models.account import StudentModel


def initialize():
    print('Initializer Started Successfully')

    while True:
        now = datetime.datetime.now()

        if now.time().hour == 0:
            for student in StudentModel.objects:
                student.extension_apply_11 = None
                student.extension_apply_12 = None

        time.sleep(1800)
