# Gitlab-Flow를 적용하여 리팩토링 진행하기 

[//]: # (여기에 대략적인 그림 넣기)


## 브랜치 전략 결정 과정

### 1. 고려 상황

    1. 1명이서 혼자서 코드를 리팩토링하는 상황이다 -> 브랜치 생성을 최소화 하고 싶었다. 
    2. Unit 테스트를 도입하기 위해, 해당 단위의 테스트가 수월하도록 프로그램 코드도 수정할 예정이다.
    3. 기능의 확장보다, 코드 개선을 하는 상황이였다.

### 2. Git 브랜치 전략 탐색 (간단 요약)
**참고글**</br>
[Git 브랜치 전략(feat. Git Flow, Github-Flow, Gitlab-Flow)](https://parkstate.tistory.com/33)

1.Git-flow

    feature > develop > release > hotfix > main ...


1. 브랜치 마다 용도(목적)이 있음
2. 병합순서는  앞에서부터 `>` 뒤로 병합
3. develop과 master 브랜치는 반드시 존재해야함

2.Github-flow

    main -> feature -> main...

1. feature 브랜치에서 구현 후, pull Request에서 코드 리뷰 후 main에 반영
2. ci/cd 구현이 된 경우 최적화

3.Gitlab-flow

    main , pre-production , production, +[new branch]

- main
  * 개발을 위한 브랜치(기능 구현에 초점), PR를 거친 커밋들이 자유롭게 올라간다.
  * production의 배포와 관계가 없다. == 배포 주기에 구애받지 않는다.

- pre-prodution == staging
  - develop에서 배포가 확정된 커밋들이 존재
  - production으로 넘어가기 전의 브랜치, 테스트 등을 담당

- production
  * 실제 배포가 되는 브랜치
  * staging에서 테스트 후, production으로 merge가 되면 배포가 완료

3.trunk-based development strategy (TBD)

    trunk(main)

- main(또는 trunk)라는 주 브랜치 하나만 운영, 신규 기능은 main에 바로 커밋하거나, 며칠 내로 main에 머지할 피처 브랜치에서 작업을 한다.
- CI가 구축된 환경에서 적합
- TDD로 개발을 진행하는 경우 적합

### 3. 주요 브랜치 전략 결정과 규칙 정하기

**결론: TBD + 약간의 Gitlab-Flow 전략이 첨가된 브랜치 전략 채택** 

**채택한 이유**
도입 당시에는 GitLab-Flow가 다음과 같은 부분에서 적합하다고 생각했다.

1. 홀로 학습목적으로 코드 리펙토링을 하는 목적이였다. (작은 팀? 규모)
2. 이미 팀원들과 Github-Action을 이용한 ci-cd 프로세스가 구현되어 있었다.
3. TDD 학습 목적
4. develop, test 환경이 구분되어 있어야 했다.

리펙토링은 TBD방식으로 진행하는 것을 채택하고  나누는 것으로 채택했다.
Gitlab-Flow의 배포 브랜치을 따로 만들어서 배포를 하는 방식을 채택했다.

#### 결론 : 결정된 리펙토링 브랜치 전략 규칙
  1. main 브랜치에서 git-flow 방식과 동일하게 기능 추가가 일어난다.
  2. staging 브랜치에서는 테스트가 실행된다.
  3. test에 성공한 staging 커밋은 prod 브랜치에 올라간다.
  4. prod에 올라간 커밋을 반영하여 지속적 배포를 한다.

### 4.Git 브랜치 전략을 다루기 위한 Github Setting 하기

### 5. 전략 사용 후기

[//]: # (1. 코드 리팩토링 목적에 적합했다.)
[//]: # (2. 개발인원이 적은 경우에 적합하다는 인상을 받았다 &#40;개발-테스트 구분이 모호한 경우 적합했다&#41;)

## 참고 URL
GitLab 이슈를 활용한 Git Branch Strategy   
출처 : https://waspro.tistory.com/710

코드 병합 전략: Git Flow, GitHub Flow 등 다양한 병합 전략과 그 장단점  
출처: https://statuscode.tistory.com/101
       
Introduction to GitLab Flow    
출처: https://docs.gitlab.cn/14.0/ee/topics/gitlab_flow.html

Choosing the Right Git Branching Strategy: A Comparative Analysis
출처: https://medium.com/@sreekanth.thummala/choosing-the-right-git-branching-strategy-a-comparative-analysis-f5e635443423

맘시터 기술블로그 - Git Flow에서 트렁크 기반 개발으로 나아가기
출처: https://tech.mfort.co.kr/blog/2022-08-05-trunk-based-development/

## 참고 동영상 자료

