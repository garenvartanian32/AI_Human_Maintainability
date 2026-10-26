#include <iostream>
#include <cstdio>
#include <algorithm>
#include <climits>
#include <functional>
using namespace std;

// 動的遅延セグメント木 (必要なところだけノードを作る)

template <typename MonoidType, typename OperatorType>
struct LazySegNode {
    MonoidType value;
    OperatorType lazy_value;
    bool need_update;
    LazySegNode *ch[2];
    
    LazySegNode() {}
    LazySegNode(MonoidType value_, OperatorType lazy_value_) :
        value(value_), lazy_value(lazy_value_) {
        need_update = false;
        ch[0] = ch[1] = nullptr;
    }
};

template <typename MonoidType, typename OperatorType, typename IdxType = int>
struct DynamicLazySegmentTree {
    using MMtoM = function< MonoidType(MonoidType, MonoidType) >;
    using OOtoO = function< OperatorType(OperatorType, OperatorType) >;
    using MOtoM = function< MonoidType(MonoidType, OperatorType) >;
    using OItoO = function< OperatorType(OperatorType, int) >;
    using LNode = LazySegNode<MonoidType, OperatorType>;

    // node, identity element
    IdxType n;
    LNode *root;
    MonoidType E0;
    OperatorType E1;

    // update / combine / lazy / accumulate function
    MOtoM upd_f;
    MMtoM cmb_f;
    OOtoO lzy_f;
    OItoO acc_f;

    DynamicLazySegmentTree() {}
    DynamicLazySegmentTree(IdxType n_, MonoidType E0_, OperatorType E1_,
                           MOtoM upd_f_, MMtoM cmb_f_, OOtoO lzy_f_, OItoO acc_f_) :
        E0(E0_), E1(E1_),
        upd_f(upd_f_), cmb_f(cmb_f_), lzy_f(lzy_f_), acc_f(acc_f_) {
        root = new LNode(E0_, E1_);
        n = 1; while(n < n_) n *= 2;
    }

    void eval(LNode *node, IdxType l, IdxType r) {
        if(!node->need_update) return;
        node->value = upd_f(node->value, acc_f(node->lazy_value, r - l));
        if(r - l > 1) {
            if(!node->ch[0]) node->ch[0] = new LNode(E0, E1);
            if(!node->ch[1]) node->ch[1] = new LNode(E0, E1);
            node->ch[0]->lazy_value = lzy_f(node->ch[0]->lazy_value, node->lazy_value);
            node->ch[1]->lazy_value = lzy_f(node->ch[1]->lazy_value, node->lazy_value);
            node->ch[0]->need_update = node->ch[1]->need_update = true;
        }
        node->lazy_value = E1;
        node->need_update = false;
    }

    void update(LNode *node, IdxType a, IdxType b, OperatorType x,
                IdxType l, IdxType r) {
        eval(node, l, r);
        if(b <= l or r <= a) return;
        if(a <= l and r <= b) {
            node->lazy_value = lzy_f(node->lazy_value, x);
            node->need_update = true;
            eval(node, l, r);
        }
        else {
            IdxType mid = (l + r) / 2;
            if(!node->ch[0]) node->ch[0] = new LNode(E0, E1);
            if(!node->ch[1]) node->ch[1] = new LNode(E0, E1);
            update(node->ch[0], a, b, x, l, mid);
            update(node->ch[1], a, b, x, mid, r);
            node->value = cmb_f(node->ch[0]->value, node->ch[1]->value);
        }
    }

    MonoidType query(LNode *node, IdxType a, IdxType b, IdxType l, IdxType r) {
        if(b <= l or r <= a) return E0;
        eval(node, l, r);
        if(a <= l and r <= b) return node->value;
        IdxType mid = (l + r) / 2;
        MonoidType vl = (node->ch[0] ? query(node->ch[0], a, b, l, mid) : E0);
        MonoidType vr = (node->ch[1] ? query(node->ch[1], a, b, mid, r) : E0);
        return cmb_f(vl, vr);
    }

    // update [a, b)-th element (applied value: x)
    void update(IdxType a, IdxType b, OperatorType x) {
        update(root, a, b, x, 0, n);
    }

    // range query for [a, b)
    MonoidType query(IdxType a, IdxType b) {
        return query(root, a, b, 0, n);
    }
};


void DSL_2_D() {
    int N, Q; cin >> N >> Q;
    DynamicLazySegmentTree<int, int> seg(N, INT_MAX, INT_MAX,
                                         [](int a, int b) { return b; },
                                         [](int a, int b) { return min(a, b); },
                                         [](int a, int b) { return b; },
                                         [](int a, int b) { return a; });

    int indexed = 0;
    for(int i=0; i<Q; i++) {
        int q; cin >> q;
        if(q == 0) {
            int s, t, x; cin >> s >> t >> x;
            s -= indexed; t -= indexed; t++;
            seg.update(s, t, x);
        }
        if(q == 1) {
            int k; cin >> k;
            k -= indexed;
            cout << seg.query(k, k+1) << endl;
        }
    }
}

// Verified on Apr 27, 2019
void DSL_2_E() {
    int N, Q; cin >> N >> Q;
    DynamicLazySegmentTree<int, int> seg(N, 0, 0,
                                         [](int a, int b) { return a + b; },  // upd
                                         [](int a, int b) { return a + b; },  // cmb
                                         [](int a, int b) { return a + b; },  // lzy
                                         [](int a, int x) { return a * x; }); // acc
    
    int indexed = 1;
    for(int i=0; i<Q; i++) {
        int query_type; cin >> query_type;
        if(query_type == 0) {
            int s, t, x; cin >> s >> t >> x; t++;
            s -= indexed, t -= indexed;
            seg.update(s, t, x);
        }
        if(query_type == 1) {
            int s; cin >> s;
            s -= indexed;
            cout << seg.query(s, s+1) << endl;
        }
        // seg.dump();
    }    
}

// Verified on Apr 27, 2019
void DSL_2_F() {
    int N, Q; cin >> N >> Q;
    DynamicLazySegmentTree<int, int> seg(N, INT_MAX, 0,
                                         [](int a, int b) { return b;         },  // upd
                                         [](int a, int b) { return min(a, b); },  // cmb
                                         [](int a, int b) { return b;         },  // lzy
                                         [](int a, int x) { return a;         }); // acc
    
    int indexed = 0;
    for(int i=0; i<Q; i++) {
        int query_type; cin >> query_type;
        if(query_type == 0) {
            int s, t, x; cin >> s >> t >> x; t++;
            s -= indexed, t -= indexed;
            seg.update(s, t, x);
        }
        if(query_type == 1) {
            int s, t; cin >> s >> t; t++;
            s -= indexed; t -= indexed;
            cout << seg.query(s, t) << endl;
        }
        // seg.dump();
    }    
}

// Verified on Apr 27, 2019
void DSL_2_G() {
    int N, Q; cin >> N >> Q;
    using ll = long long int;
    DynamicLazySegmentTree<ll, ll> seg(N, 0, 0,
                                       [](ll a, ll  b) { return a + b; },  // upd
                                       [](ll a, ll  b) { return a + b; },  // cmb
                                       [](ll a, ll  b) { return a + b; },  // lzy
                                       [](ll a, int x) { return a * x; }); // acc
    
    int indexed = 1;
    for(int i=0; i<Q; i++) {
        int query_type; cin >> query_type;
        if(query_type == 0) {
            int s, t, x; cin >> s >> t >> x; t++;
            s -= indexed, t -= indexed;
            seg.update(s, t, x);
        }
        if(query_type == 1) {
            int s, t; cin >> s >> t; t++;
            s -= indexed; t -= indexed;
            cout << seg.query(s, t) << endl;
        }
        // seg.dump();
    }    
}

// Verified on Apr 27, 2019
void DSL_2_H() {
    int N, Q; cin >> N >> Q;
    using ll = long long int;
    using Pair = pair<ll, ll>;
    DynamicLazySegmentTree<ll, Pair> seg(N, INT_MAX, make_pair(0, 0),
                                       [](ll a, Pair b) {
                                             if(b.second == 1) {
                                                 return b.first;
                                             }
                                             else {
                                                 return a + b.first;
                                             }
                                         },  // upd
                                       [](ll a, ll  b) { return min(a, b); },  // cmb
                                       [](Pair a, Pair b) {
                                           if(a.second == 0 and b.second == 0) {
                                               return make_pair(a.first + b.first, 0LL);
                                           }
                                           if(a.second == 0 and b.second == 1) {
                                               return b;
                                           }
                                           if(a.second == 1 and b.second == 0) {
                                               return make_pair(a.first + b.first, 1LL);
                                           }
                                           if(a.second == 1 and b.second == 1) {
                                               return b;
                                           }
                                       },
                                       [](Pair a, int x) { return a;         }); // acc
    
    int indexed = 0;
    for(int i=0; i<N; i++) {
        seg.update(i, i+1, make_pair(0, 1));
    }
    for(int i=0; i<Q; i++) {
        int query_type; cin >> query_type;
        if(query_type == 0) {
            int s, t, x; cin >> s >> t >> x; t++;
            s -= indexed, t -= indexed;
            seg.update(s, t, make_pair(x, 0));
        }
        if(query_type == 1) {
            int s, t; cin >> s >> t; t++;
            s -= indexed; t -= indexed;
            cout << seg.query(s, t) << endl;
        }
        // seg.dump();
    }    
}

// Verified on Apr 27, 2019
void DSL_2_I() {
    int N, Q; cin >> N >> Q;
    using ll = long long int;
    DynamicLazySegmentTree<ll, ll> seg(N, 0, 0,
                                       [](ll a, ll  b) { return b;     },  // upd
                                       [](ll a, ll  b) { return a + b; },  // cmb
                                       [](ll a, ll  b) { return b;     },  // lzy
                                       [](ll a, int x) { return a * x; }); // acc
    
    int indexed = 0;
    for(int i=0; i<Q; i++) {
        int query_type; cin >> query_type;
        if(query_type == 0) {
            int s, t, x; cin >> s >> t >> x; t++;
            s -= indexed, t -= indexed;
            seg.update(s, t, x);
        }
        if(query_type == 1) {
            int s, t; cin >> s >> t; t++;
            s -= indexed; t -= indexed;
            cout << seg.query(s, t) << endl;
        }
        // seg.dump();
    }    
}

int main() {
    // DSL_2_D();
    // DSL_2_E();
    // DSL_2_F();
    // DSL_2_G();
    // DSL_2_H();
    DSL_2_I();
    return 0;
}

