import threading

from app import app
from support.apply_initializer.initializer import initialize


if __name__ == '__main__':
    # db_migrator.migrate_posts()
    # meal.parse()

    threading.Thread(target=initialize).start()
    app.run(host=app.config['HOST'], port=app.config['PORT'], debug=app.debug, threaded=True)
