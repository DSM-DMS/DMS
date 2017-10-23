def list(model):
    return [{
        'id': str(post.id),
        'title': post.title,
        'author': post.author.name,
        'write_date': post.write_date
    } for post in model.objects]


def inquire(model, id):
    post = model.objects(id=id).first()

    if not post:
        return None
    else:
        return {
            'id': str(post.id),
            'title': post.title,
            'author': post.author.name,
            'write_date': post.write_date,
            'content': post.content
        }
