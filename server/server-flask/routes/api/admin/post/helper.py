def post(model, title, content, admin):
    model(title=title, content=content, author=admin).save()


def patch(model, id, title, content):
    post = model.objects(id=id).first()
    post.update(title=title, content=content)


def delete(model, id):
    model.objects(id=id).first().delete()
