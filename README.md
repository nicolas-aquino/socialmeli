# SocialMeli

- [Trello](https://trello.com/b/wJBNHdHB/bejavahispw25g05)
- [Excalibur (Diagrama de clases)](https://excalidraw.com/#room=3525b414b8674fc9a010,4H2FJFBKAW5q2_6V6Tcg4w)
- [Especificación técnica funcional](https://www.google.com/url?q=https://docs.google.com/document/d/1Q-xGaOMPij-qk_gMvcN0Sk0isbCPqjJS/edit?usp%3Dsharing%26ouid%3D109742565608469686147%26rtpof%3Dtrue%26sd%3Dtrue&sa=D&source=editors&ust=1708099270823216&usg=AOvVaw2JhBoFP4pvFzUyAwHRjz3Z)

# Git Flow (Robado de [The Odin Project](https://www.theodinproject.com/lessons/ruby-using-git-in-the-real-world#ongoing-workflow)

#### Ongoing workflow

We've got one main branch -- `main`. `main` is for production-ready code. Any code deployed to `main` (on the original repo, not on your fork) will be tested in staging and shipped to production. You'll be working in a feature branch and submitting your pull requests to the `main` branch.

1. Create a new feature branch for whatever feature you want to build, and add commits following the same practices that you learned about in the [branching section of our Revisiting Rock Paper Scissors lesson](https://www.theodinproject.com/lessons/foundations-revisiting-rock-paper-scissors#using-branches).
1. When you're done with your feature, odds are that someone has made changes to the upstream repository in the meantime. That means that your `main` branch is probably out of date. Fetch the most updated copy using `git fetch upstream`.
1. Now merge the upstream's changes into your local version of `main` using `git merge`. Specifically, you'll first want to make sure you're on your `main` branch using `git checkout main` and then `git merge upstream/main` to merge in those upstream changes that we just fetched.
1. Note that a `git fetch upstream` followed by a `git merge upstream/some_branch` is the EXACT same thing as doing a `git pull upstream some_branch`. We prefer to split it up here so that we can explicitly walk through the steps.
1. Now that your `main` branch is up-to-date with upstream, you need to merge it into your feature branch. Yes, that is correct and it seems odd at first. Don't you want to merge the feature branch into the `main` branch instead? Yes, you do, *but not yet*. **Your feature branch is dirty.** You don't know if it has any conflicts which might creep up. Any time you are merging in more "senior" branches (e.g. merging the feature into `main`), you want it to be a clean and conflict-free merge if possible. So you first merge the "senior" branch into your dirty branch to resolve those conflicts. Run `git checkout your_feature_name` to jump back onto your feature branch, then `git merge main` to merge `main` into it.
1. You may have merge conflicts... resolve those using the skills you learned in [the Deeper Look at Git lesson](https://www.theodinproject.com/lessons/ruby-a-deeper-look-at-git)! ([JS Course Link](https://www.theodinproject.com/lessons/javascript-a-deeper-look-at-git)).

#### Sending your pull request

1. Now that your feature branch is squeaky clean and you know it'll merge cleanly into `main`, the hard part is all over. All that's left is to make the Pull Request (often abbreviated as PR) against our `upstream` repo on GitHub!
1. Now you want to send your feature branch back up to your `origin` (your fork of the `upstream` repository). You can't send directly to `upstream` because you don't have access, so you'll need to make a pull request. Use `git push origin your_feature_name` to ship your feature branch up to your fork on GitHub.
1. If you have been following along with the above steps to get familiar with this workflow, you should **stop at this point**. If you have completed an assigned issue, the final step is to submit a pull request to merge your feature branch into the original `upstream` repository's `main` branch. This can be done using GitHub's interface.
1. Shake your moneymaker, you're an OSS contributor!

## A. Escenario y requerimientos iniciales (Desarrollo GRUPAL)
Mercado Libre sigue creciendo y para el año que viene  tiene como objetivo empezar a implementar una serie de herramientas que permitan a los compradores y vendedores tener una experiencia totalmente innovadora, en donde el lazo que los una sea mucho más cercano.

La fecha de lanzamiento se aproxima, por lo cual es necesaria la presentación de una versión Beta de lo que va a ser conocido como __"SocialMeli"__, en donde los compradores van a poder seguir a sus vendedores favoritos y enterarse de todas las novedades que los mismos posteen.

Para poder realizar esto, un analista funcional relevó una serie de requerimientos que deben llevarse a cabo; sin embargo, como cuentan con una determinada complejidad y los tiempos son escasos, deberán ser ejecutados en __equipos de trabajo__. Los mismos se detallan a continuación:


### Se plantea creación de una API Rest que permita:

1. Poder realizar la acción de “Follow” (seguir) a un determinado usuario
2. Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
3. Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
4. Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
5. Dar de alta una nueva publicación.
6. Obtener un listado de las publicaciones realizadas en las últimas dos semanas, por los vendedores que un usuario sigue (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
7. Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.

Por otra parte, dado que se pretende una buena experiencia de usuario con respecto a la forma de presentación de los resultados de cada consulta, se necesita que los mismos puedan ser ordenados mediante cualquiera de los siguientes criterios:

8. Alfabético Ascendente y Descendente
9. Fecha Ascendente y Descendente

Un analista funcional llevó a cabo el __relevamiento de los requerimientos técnicos funcionales__ y ha proporcionado la documentación que se cita a continuación para tener en cuenta a la hora de llevar a cabo el desarrollo correspondiente:


[Especificación técnica funcional](https://www.google.com/url?q=https://docs.google.com/document/d/1Q-xGaOMPij-qk_gMvcN0Sk0isbCPqjJS/edit?usp%3Dsharing%26ouid%3D109742565608469686147%26rtpof%3Dtrue%26sd%3Dtrue&sa=D&source=editors&ust=1708099270823216&usg=AOvVaw2JhBoFP4pvFzUyAwHRjz3Z)
