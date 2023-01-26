#                                                        Projet NFC : Gestion des absences :

# I. Contexte :
L'application mobile de gestion d'absence des étudiants  est conçue pour simplifier le processus de suivi des absences dans une école ou une université. Elle utilise la technologie NFC pour permettre aux étudiants de marquer leur présence en utilisant leur tag NFC. Lorsqu'un étudiant arrive en classe, il peut simplement passer son tag NFC devant un smartphone. L'application enregistre automatiquement la présence de l'étudiant et met à jour son dossier d'absence. Les enseignants peuvent également utiliser l'application pour voir les absences de chaque étudiant, et pour suivre les tendances d'absentéisme dans leur classe.

# II. Application :
## 1. Ecriture :
L’application écris dans un tag vierge l’id de l’étudiant auquel il appartient , chaque etudiant ayant un enregistrement dans la base de donnée.

## 2. Lecture :
L'application lis le contenu du tag de chaque étudiant, et ainsi chaque étudiant ayant badgé est noté présent dans la liste d'absence.

# III. Technologies utilisées :
Le développement de l'application a été  fait en Android native , la partie base de donnée est assurée par Firebase .
![0_KMNMu-xZGVXh-_zZ](https://user-images.githubusercontent.com/101510983/214884276-ec8d4b31-5d68-4e62-afb6-258e98b3f16d.png)
![png-clipart-android-studio-integrated-development-environment-java-mobile-app-development-android-logo-mobile-app-development](https://user-images.githubusercontent.com/101510983/214884732-b66353f7-2e4d-4f92-a72d-415a0e5c3d6e.png)
