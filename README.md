#                                                        Projet NFC : Gestion des absences :

- [Projet NFC : Gestion des absences :](#projet-nfc---gestion-des-absences--)
- [I. Contexte :](#i-contexte--)
- [II. Application :](#ii-application--)
  * [1. Ecriture :](#1-ecriture--)
  * [2. Lecture :](#2-lecture--)
- [III. Technologies utilisées :](#iii-technologies-utilis-es--)
  * [Réalisé par :](#r-alis--par--)









# I. Contexte :
L'application mobile de gestion d'absence des étudiants  est conçue pour simplifier le processus de suivi des absences dans une école ou une université. Elle utilise la technologie NFC pour permettre aux étudiants de marquer leur présence en utilisant leur tag NFC. Lorsqu'un étudiant arrive en classe, il peut simplement passer son tag NFC devant un smartphone. L'application enregistre automatiquement la présence de l'étudiant et met à jour son dossier d'absence. Les enseignants peuvent également utiliser l'application pour voir les absences de chaque étudiant, et pour suivre les tendances d'absentéisme dans leur classe.

# II. Application :
## 1. Ecriture :
L’application écris dans un tag vierge l’id de l’étudiant auquel il appartient , chaque etudiant ayant un enregistrement dans la base de donnée.

## 2. Lecture :
L'application lis le contenu du tag de chaque étudiant, et ainsi chaque étudiant ayant badgé est noté présent dans la liste d'absence.

# III. Technologies utilisées :
Le développement de l'application a été  fait en Android native , la partie base de donnée est assurée par Firebase .

![firebase-ar21](https://user-images.githubusercontent.com/101510983/214885026-af818c8f-768c-4b3d-b608-9ed4c45b7d2e.svg)
![icons8-android-studio-48](https://user-images.githubusercontent.com/101510983/214885361-bd9cce07-749f-4c0b-b509-477ec0cf2fe4.png)


## Réalisé par :

Argane Hamza

Dadda Farouk

El garti Fatima

Sedyame Hamza 

Hajjam Ismail
