from app import app


if __name__ == '__main__':
    # db_migrator.migrate_posts()
    # meal.parse()

    app.run(host=app.config['HOST'], port=app.config['PORT'], debug=app.debug, threaded=True)
